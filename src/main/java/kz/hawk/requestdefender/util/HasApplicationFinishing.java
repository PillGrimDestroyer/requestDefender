package kz.hawk.requestdefender.util;

public interface HasApplicationFinishing {

  void onApplicationFinishing() throws Exception;

  default double stoppingOrderIndex() {
    return 10.0;
  }

}
