package kz.hawk.requestdefender.impl;

import kz.hawk.requestdefender.dao.SecretDao;
import kz.hawk.requestdefender.dao.SecretTestDao;
import kz.hawk.requestdefender.model.dao.Secret;
import kz.hawk.requestdefender.model.request.CheckRequest;
import kz.hawk.requestdefender.model.request.PrepareRequest;
import kz.hawk.requestdefender.register.SecretRegister;
import kz.hawk.requestdefender.util.Json;
import kz.hawk.requestdefender.util.MD5;
import kz.hawk.requestdefender.util.ParentTestNG;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SecretRegisterImplTest extends ParentTestNG {

  @Autowired
  private SecretDao secretDao;

  @Autowired
  private SecretTestDao ss;

  @Autowired
  private SecretRegister secretRegister;

  @Test
  public void testPrepareRequest() {
    var userSecret = BigInteger.valueOf(new Random().nextInt(Integer.MAX_VALUE - 1) + 1);

    var req = new PrepareRequest();
    req.setModule(new BigInteger("2").pow(521).subtract(BigInteger.ONE));
    req.setGenerator(BigInteger.valueOf(new Random().nextInt(Integer.MAX_VALUE - 1) + 1));
    req.setUserResult(req.getGenerator().modPow(userSecret, req.getModule()));

    //
    //
    var resp = secretRegister.prepareRequest(req);
    //
    //

    var serverFinalSecret = secretDao.getById(resp.getId());
    var clientFinalSecret = resp.getServerResult().modPow(userSecret, req.getModule());

    assertThat(serverFinalSecret.getSecret()).isEqualTo(clientFinalSecret);
  }

  @Test
  @SneakyThrows
  public void testCheckRequest() {
    var userSecret   = BigInteger.valueOf(new Random().nextInt(Integer.MAX_VALUE - 1) + 1);
    var serverSecret = BigInteger.valueOf(new Random().nextInt(Integer.MAX_VALUE - 1) + 1);

    var secret = new Secret();

    secret.setId(idGenerator.generateId());
    secret.setModule(new BigInteger("2").pow(521).subtract(BigInteger.ONE));
    secret.setGenerator(BigInteger.valueOf(new Random().nextInt(Integer.MAX_VALUE - 1) + 1));
    secret.setServerSecret(serverSecret);
    secret.setUserResult(secret.getGenerator().modPow(userSecret, secret.getModule()));
    secret.setServerResult(secret.getGenerator().modPow(secret.getServerSecret(), secret.getModule()));
    secret.setSecret(secret.getUserResult().modPow(secret.getServerSecret(), secret.getModule()));

    secretDao.save(secret);

    var body = new HashMap<String, Object>();
    body.put("test", "Hello");
    body.put("date", Date.from(Instant.now()));
    body.put("superBigNumber", new BigInteger("525482138544628141584182445434468861621335467"));

    var sortedBody = body.entrySet().stream()
                         .sorted(Map.Entry.comparingByKey())
                         .collect(Collectors.toMap(
                             Map.Entry::getKey,
                             Map.Entry::getValue,
                             (oldValue, newValue) -> oldValue,
                             LinkedHashMap::new
                         ));

    sortedBody.put(secret.getUserResult().toString(), secret.getSecret().toString());

    var req = new CheckRequest();
    req.setSecretId(secret.getId());
    req.setBody(body);
    req.setHeader(MD5.encode(Json.toString(sortedBody)));

    //
    //
    var resp = secretRegister.checkRequest(req);
    //
    //

    System.out.print("Request: ");
    System.out.println(Json.toPrettyString(req));
    System.out.println();

    System.out.println("SortedBody: ");
    System.out.println(Json.toPrettyString(sortedBody));
    System.out.println();

    System.out.print("Response: ");
    System.out.println(Json.toPrettyString(resp));
    System.out.println();

    assertThat(resp).isNotNull();
    assertThat(resp.isValid()).isTrue();
    assertThat(resp.getErrorMsg()).isBlank();

  }
}
