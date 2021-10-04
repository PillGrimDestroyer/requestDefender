package kz.hawk.requestdefender.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.UUID;

@Data
public class CheckRequest {

  @NotEmpty(message = "'secretId' is required")
  private UUID secretId;

  @NotEmpty(message = "'header' is required")
  private String header;
  
  @NotNull(message = "'body' is required")
  @Size(min = 1, message = "'body' must be filled")
  private Map<String, Object> body;

}
