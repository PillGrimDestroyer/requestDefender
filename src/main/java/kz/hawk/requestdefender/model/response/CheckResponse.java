package kz.hawk.requestdefender.model.response;

import lombok.Data;

@Data
public class CheckResponse {
  private boolean isValid;
  private String  errorMsg;


  public static CheckResponse ofOk() {
    var resp = new CheckResponse();

    resp.setValid(true);

    return resp;
  }

  public static CheckResponse ofError(String errorMsg) {
    var resp = new CheckResponse();

    resp.setValid(false);
    resp.setErrorMsg(errorMsg);

    return resp;
  }
}
