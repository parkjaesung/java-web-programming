package next.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import next.dao.QuestionDao;
import next.support.init.DatabaseInitializer;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import core.jdbc.JdbcTemplate;

@Configuration
@PropertySource(value="classpath:application-properties.xml")
public class Config {
	@Resource
	private Environment env;
	
	@Bean(initMethod="initialize")
	public DatabaseInitializer dbInitializer() {
		return new DatabaseInitializer(dataSource());
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getRequiredProperty("database.driverClassName"));
		ds.setUrl(env.getRequiredProperty("database.url"));
		ds.setUsername(env.getRequiredProperty("database.username"));
		ds.setPassword(env.getRequiredProperty("database.password"));
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public QuestionDao questionDao() {
		QuestionDao questionDao = new QuestionDao();
		questionDao.setJdbcTemplate(jdbcTemplate());
		return questionDao;
	}
}
