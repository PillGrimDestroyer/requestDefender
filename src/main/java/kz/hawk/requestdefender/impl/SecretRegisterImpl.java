package kz.hawk.requestdefender.impl;

import kz.hawk.requestdefender.dao.SecretDao;
import kz.hawk.requestdefender.model.dao.Secret;
import kz.hawk.requestdefender.model.request.PrepareRequest;
import kz.hawk.requestdefender.model.response.PrepareResponse;
import kz.hawk.requestdefender.register.SecretRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import java.math.BigInteger;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecretRegisterImpl implements SecretRegister {
  
  private final IdGenerator idGenerator;
  private final SecretDao   secretDao;
  
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
  
}
