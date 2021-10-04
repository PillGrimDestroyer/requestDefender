package kz.hawk.requestdefender;

import kz.hawk.requestdefender.beans.ApplicationFinisher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@RequiredArgsConstructor
public class RequestDefenderApplication {

  private final ApplicationFinisher applicationFinisher;

  public static void main(String[] args) {
    SpringApplication.run(RequestDefenderApplication.class, args);
  }

  @PreDestroy
  public void preDestroy() {
    applicationFinisher.finishApplication();
  }
}
