package kz.hawk.requestdefender.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.Description;
import kz.greetgo.conf.hot.FirstReadEnv;

@Description("Настройки для управления секретами")
public interface SecretRegisterConfig {

  @Description("Максимальная продолжительность хранения секретов. Указывается в днях")
  @DefaultIntValue(1)
  @FirstReadEnv("SECRET_DAYS")
  Integer storeDays();

}
