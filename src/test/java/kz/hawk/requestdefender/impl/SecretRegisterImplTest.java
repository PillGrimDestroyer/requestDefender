package kz.hawk.requestdefender.impl;

import kz.hawk.requestdefender.dao.SecretDao;
import kz.hawk.requestdefender.model.dao.Secret;
import kz.hawk.requestdefender.model.request.PrepareRequest;
import kz.hawk.requestdefender.register.SecretRegister;
import kz.hawk.requestdefender.util.ParentTestNG;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.IdGenerator;

import java.math.BigInteger;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class SecretRegisterImplTest extends ParentTestNG {
  
  @Autowired
  private IdGenerator idGenerator;
  
  @Autowired
  private SecretDao secretDao;
  
  @Autowired
  private SecretRegister secretRegister;
  
  @Test
  void test() {
    Secret secret = new Secret();
    
    secret.setId(idGenerator.generateId());
    
    secretDao.save(secret);
    
    secret.setModule(new BigInteger("2").pow(521).subtract(BigInteger.ONE));
    
    secretDao.save(secret);
    
    System.out.println(secret);
  }
  
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
    
    var serverFinalSecret = secretDao.getById(resp.getId().toString());
    var clientFinalSecret = resp.getServerResult().modPow(userSecret, req.getModule());
    
    assertThat(serverFinalSecret.getSecret()).isEqualTo(clientFinalSecret);
  }
}
