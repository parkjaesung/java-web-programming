package next.config;

import next.support.init.DatabaseInitializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean(initMethod="initialize")
	public DatabaseInitializer dbInitializer() {
		return new DatabaseInitializer();
	}
}
