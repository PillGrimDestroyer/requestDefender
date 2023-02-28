package kz.hawk.requestdefender.dao;

import org.apache.ibatis.annotations.Select;

public interface SecretTestDao {

  @Select("select exists(SELECT 1 FROM secret)")
  boolean test();


}
