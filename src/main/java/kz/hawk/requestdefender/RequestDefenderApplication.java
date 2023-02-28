package kz.hawk.requestdefender;

import kz.hawk.requestdefender.beans.all.ApplicationFinisher;
import kz.hawk.requestdefender.beans.all.BeanConfigAll;
import kz.hawk.requestdefender.beans.prod.BeanConfigProd;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PreDestroy;

@SpringBootApplication
@RequiredArgsConstructor
@Import({BeanConfigAll.class, BeanConfigProd.class})
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
