package next.config;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigTest {

	@Test
	public void test() {
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
				Config.class)) {
			DataSource dataSource = ac.getBean(DataSource.class);
			assertNotNull(dataSource);
		}
	}

}
