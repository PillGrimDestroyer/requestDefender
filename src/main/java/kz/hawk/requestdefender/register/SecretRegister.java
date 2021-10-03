package kz.hawk.requestdefender.register;

import kz.hawk.requestdefender.model.request.PrepareRequest;
import kz.hawk.requestdefender.model.response.PrepareResponse;

public interface SecretRegister {
  
  PrepareResponse prepareRequest(PrepareRequest prepareRequest);
}
