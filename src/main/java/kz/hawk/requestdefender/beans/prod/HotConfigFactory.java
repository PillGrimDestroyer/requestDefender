package kz.hawk.requestdefender.beans.prod;

import kz.greetgo.conf.hot.FileConfigFactory;
import kz.hawk.requestdefender.config.DbConfig;
import kz.hawk.requestdefender.config.SecretRegisterConfig;
import kz.hawk.requestdefender.util.App;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class HotConfigFactory extends FileConfigFactory {

  @Override
  protected Path getBaseDir() {
    return App.appDir().resolve("config");
  }

  @Bean
  public DbConfig dbConfig() {
    return createConfig(DbConfig.class);
  }

  @Bean
  public SecretRegisterConfig secretRegisterConfig() {
    return createConfig(SecretRegisterConfig.class);
  }
}
