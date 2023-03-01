package kz.hawk.requestdefender.dao;

import kz.hawk.requestdefender.model.dao.Secret;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface SecretDao {

  @Insert(
      "insert into secret(id, server_secret, user_result, module, generator, server_result, \"secret\") " +
          "values " +
          "(#{x.id}, #{x.serverSecret}, #{x.userResult}, #{x.module}, #{x.generator}, #{x.serverResult}, #{x.secret}) " +
          "on conflict (id) do update " +
          "set server_secret = #{x.serverSecret}, user_result = #{x.userResult}, module = #{x.module}, generator = #{x.generator}, " +
          "server_result = #{x.serverResult}, \"secret\" = #{x.secret}"
  )
  void save(@Param("x") Secret secret);

  @Select(
      "select * from secret where id = #{id}"
  )
  Secret getById(@Param("id") UUID id);

  @Delete(
      "delete from secret " +
          "where created_at < now() - (#{d}::varchar || ' days')::interval"
  )
  void deleteOldSecrets(@Param("d") Integer days);
}
