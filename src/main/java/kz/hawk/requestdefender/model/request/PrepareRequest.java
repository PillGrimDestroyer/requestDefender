package kz.hawk.requestdefender.model.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class PrepareRequest {
  @NotNull(message = "The 'userResult' is required")
  @Min(value = 1, message = "The 'userResult' is must be greater or equal 1")
  private BigInteger userResult;

  @NotNull(message = "The 'generator' is required")
  @Min(value = 1, message = "The 'generator' is must be greater or equal 1")
  private BigInteger generator;

  @NotNull(message = "The 'module' is required")
  @Min(value = 1, message = "The 'module' is must be greater or equal 1")
  private BigInteger module;
}
