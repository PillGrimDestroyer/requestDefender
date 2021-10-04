package kz.hawk.requestdefender.scheduler;

import kz.greetgo.scheduling.FromConfig;
import kz.greetgo.scheduling.HasScheduled;
import kz.greetgo.scheduling.Scheduled;
import kz.hawk.requestdefender.register.SecretRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecretScheduler implements HasScheduled {

  private final SecretRegister secretRegister;

  @Scheduled("00:01")
  @FromConfig("Clear old secrets")
  public void clearSecrets() {
    log.info("[clearSecrets] schedule start");
    secretRegister.clearOldSecrets();
    log.info("[clearSecrets] schedule finish");
  }
}
