package kz.hawk.requestdefender.register;

import kz.hawk.requestdefender.model.request.CheckRequest;
import kz.hawk.requestdefender.model.request.PrepareRequest;
import kz.hawk.requestdefender.model.response.CheckResponse;
import kz.hawk.requestdefender.model.response.PrepareResponse;

public interface SecretRegister {
  
  PrepareResponse prepareRequest(PrepareRequest prepareRequest);
  
  CheckResponse checkRequest(CheckRequest checkRequest);
}
