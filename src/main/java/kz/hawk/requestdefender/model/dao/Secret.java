package kz.hawk.requestdefender.model.dao;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class Secret {
  private UUID       id;
  private BigInteger serverSecret;
  private BigInteger userResult;
  private BigInteger module;
  private BigInteger generator;
  private BigInteger serverResult;
  private BigInteger secret;
}
