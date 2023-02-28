package kz.hawk.requestdefender.config;

import org.springframework.stereotype.Component;

@Component
public class SecretRegisterConfigForTests implements SecretRegisterConfig {

  @Override
  public Integer storeDays() {
    return 1;
  }

}
