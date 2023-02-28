package kz.hawk.requestdefender.config;

import org.springframework.stereotype.Component;

@Component
public class DbConfigForTests implements DbConfig {

  @Override
  public String host() {
    return "localhost";
  }

  @Override
  public int port() {
    return 13432;
  }

  @Override
  public String dbName() {
    return "request_defender";
  }

  @Override
  public String username() {
    return "request_defender";
  }

  @Override
  public String password() {
    return "111";
  }

}
