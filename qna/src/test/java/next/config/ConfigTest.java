package next.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigTest {

	@Test
	public void test() {
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
				Config.class)) {
		}
	}

}
