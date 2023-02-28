package kz.hawk.requestdefender.beans.all;

import kz.hawk.requestdefender.util.HasApplicationFinishing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationFinisher {

  private final List<HasApplicationFinishing> hasApplicationFinishing;

  public void finishApplication() {
    hasApplicationFinishing
        .stream()
        .sorted(Comparator.comparing(HasApplicationFinishing::stoppingOrderIndex))
        .forEachOrdered(x -> {
          var applicationInfo = "(order " + x.stoppingOrderIndex() + ") " + x.getClass();
          try {
            x.onApplicationFinishing();
            log.info("L55fU1dR10bco :: Finished " + applicationInfo);
          } catch (Exception e) {
            log.error("tBDpe8dYG9tVz :: ERROR while finishing " + applicationInfo, e);
          }
        });
  }
}
