package next.support.init;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
	
	private DataSource dataSource;
	
	@Autowired
	public DatabaseInitializer(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("qna.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
		
		logger.info("Initialized Database Schema!");
	}
}
