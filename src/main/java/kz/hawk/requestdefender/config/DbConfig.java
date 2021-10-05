package kz.hawk.requestdefender.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;
import kz.greetgo.conf.hot.FirstReadEnv;

@Description("Настройки для управлением подключением к БД")
public interface DbConfig {

  @Description("Хост postgres")
  @DefaultStrValue("localhost")
  @FirstReadEnv("DB_HOST")
  String host();

  @Description("Порт postgres")
  @DefaultIntValue(13432)
  @FirstReadEnv("DB_PORT")
  int port();

  @Description("Имя БД postgres")
  @DefaultStrValue("request_defender")
  @FirstReadEnv("DB_NAME")
  String dbName();

  @Description("Имя пользователя в БД для доступа к нему")
  @DefaultStrValue("request_defender")
  @FirstReadEnv("DB_USER")
  String username();

  @Description("Пароль доступа к БД")
  @DefaultStrValue("111")
  @FirstReadEnv("DB_PASS")
  String password();

}
