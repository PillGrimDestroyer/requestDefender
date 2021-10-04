package kz.hawk.requestdefender.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.Description;

@Description("Настройки для управления секретами")
public interface SecretRegisterConfig {

  @Description("Максимальная продолжительность хранения секретов. Указывается в днях")
  @DefaultIntValue(1)
  Integer storeDays();

}
