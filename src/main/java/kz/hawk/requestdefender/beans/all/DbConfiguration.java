package kz.hawk.requestdefender.beans.all;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.hawk.requestdefender.config.DbConfig;
import kz.hawk.requestdefender.dao.BeanConfigDao;
import kz.hawk.requestdefender.type_handler.UuidTypeHandler;
import liquibase.integration.spring.SpringLiquibase;
import lombok.SneakyThrows;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackageClasses = BeanConfigDao.class)
public class DbConfiguration {

  @Autowired
  private DbConfig dbConfig;

  @Bean
  public IdGenerator idGenerator() {
    return new AlternativeJdkIdGenerator();
  }

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

  @Bean
  public SpringLiquibase liquibase() {
    var liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
    liquibase.setDataSource(dataSource());
    return liquibase;
  }

  @Bean
  @SneakyThrows
  public SqlSessionFactory sqlSessionFactory() {
    var configuration = new org.apache.ibatis.session.Configuration();
    configuration.setJdbcTypeForNull(JdbcType.NULL);
    configuration.setLogImpl(Slf4jImpl.class);
    configuration.setMapUnderscoreToCamelCase(true);

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource());
    sqlSessionFactoryBean.setConfiguration(configuration);
    sqlSessionFactoryBean.setTypeHandlers(new UuidTypeHandler());

    return sqlSessionFactoryBean.getObject();
  }

}
