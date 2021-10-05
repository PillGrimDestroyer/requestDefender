package kz.hawk.requestdefender.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.hawk.requestdefender.config.SecretRegisterConfig;
import kz.hawk.requestdefender.dao.SecretDao;
import kz.hawk.requestdefender.model.dao.Secret;
import kz.hawk.requestdefender.model.request.CheckRequest;
import kz.hawk.requestdefender.model.request.PrepareRequest;
import kz.hawk.requestdefender.model.response.CheckResponse;
import kz.hawk.requestdefender.model.response.PrepareResponse;
import kz.hawk.requestdefender.register.SecretRegister;
import kz.hawk.requestdefender.util.Json;
import kz.hawk.requestdefender.util.MD5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecretRegisterImpl implements SecretRegister {

  private final IdGenerator          idGenerator;
  private final SecretDao            secretDao;
  private final SecretRegisterConfig config;

  @Override
  public PrepareResponse prepareRequest(PrepareRequest prepareRequest) {
    var serverSecret = BigInteger.valueOf(new Random().nextInt(Integer.MAX_VALUE - 1) + 1);

    var secret = new Secret();

    secret.setId(idGenerator.generateId());
    secret.setModule(prepareRequest.getModule());
    secret.setGenerator(prepareRequest.getGenerator());
    secret.setUserResult(prepareRequest.getUserResult());
    secret.setServerSecret(serverSecret);
    secret.setServerResult(secret.getGenerator().modPow(secret.getServerSecret(), secret.getModule()));
    secret.setSecret(secret.getUserResult().modPow(secret.getServerSecret(), secret.getModule()));

    log.info("Generate secret for '" + secret.getId() + "'");

    secretDao.save(secret);

    var response = new PrepareResponse();

    response.setServerResult(secret.getServerResult());
    response.setId(secret.getId());

    return response;
  }

  @Override
  public CheckResponse checkRequest(CheckRequest checkRequest) {
    String jsonSortedBody;
    var    secret = secretDao.getById(checkRequest.getSecretId().toString());

    if (secret == null || secret.getSecret() == null) {
      return CheckResponse.ofError("Unknown request secret");
    }

    var sortedBody = checkRequest.getBody().entrySet().stream()
                                 .sorted(Map.Entry.comparingByKey())
                                 .collect(Collectors.toMap(
                                   Map.Entry::getKey,
                                   Map.Entry::getValue,
                                   (oldValue, newValue) -> oldValue,
                                   LinkedHashMap::new
                                 ));

    sortedBody.put(secret.getUserResult().toString(), secret.getSecret().toString());

    try {
      jsonSortedBody = Json.toString(sortedBody);
    } catch (JsonProcessingException e) {
      log.error("Не удалось конвертнуть тело запроса в json строку", e);

      return CheckResponse.ofError("Bad request body");
    }

    return MD5.encode(jsonSortedBody).equals(checkRequest.getHeader())
           ? CheckResponse.ofOk()
           : CheckResponse.ofError("Wrong request body");
  }

  @Override
  public void clearOldSecrets() {
    log.info("Deleting secrets older than " + config.storeDays() + " days");
    secretDao.deleteOldSecrets(config.storeDays());
  }

}
