package kz.hawk.requestdefender.util;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class MD5 {
  
  @NotNull
  @SneakyThrows
  public static String encode(@NotNull String value) {
    var md5 = MessageDigest.getInstance("MD5");
    md5.update(value.getBytes());
    
    return DatatypeConverter.printHexBinary(md5.digest()).toUpperCase();
  }
  
}
