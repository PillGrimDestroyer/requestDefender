package kz.hawk.requestdefender.beans;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.hawk.requestdefender.config.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatasourceBean {
  
  @Autowired
  private DbConfig dbConfig;
  
  @Bean
  public DataSource dataSource() {
    var url = "jdbc:postgresql://" + dbConfig.host() + ":" + dbConfig.port() + "/" + dbConfig.dbName();
    
    var config = new HikariConfig();
    
    config.setDriverClassName("org.postgresql.Driver");
    config.setJdbcUrl(url);
    config.setUsername(dbConfig.username());
    config.setPassword(dbConfig.password());
    config.setMaximumPoolSize(50);
    
    return new HikariDataSource(config);
  }
  
}
