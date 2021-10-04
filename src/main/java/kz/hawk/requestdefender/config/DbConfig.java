package kz.hawk.requestdefender.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Настройки для управлением подключением к БД")
public interface DbConfig {

  @Description("Хост postgres")
  @DefaultStrValue("localhost")
  String host();

  @Description("Порт postgres")
  @DefaultIntValue(13432)
  int port();

  @Description("Имя БД postgres")
  @DefaultStrValue("request_defender")
  String dbName();

  @Description("Имя пользователя в БД для доступа к нему")
  @DefaultStrValue("request_defender")
  String username();

  @Description("Пароль доступа к БД")
  @DefaultStrValue("111")
  String password();

}
