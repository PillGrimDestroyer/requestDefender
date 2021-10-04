package kz.hawk.requestdefender.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
  
  public static <T> String toString(T value) throws JsonProcessingException {
    var mapper = new ObjectMapper();
    return mapper.writeValueAsString(value);
  }
  
}
