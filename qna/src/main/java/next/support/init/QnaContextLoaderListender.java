package next.support.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import next.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QnaContextLoaderListender implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(QnaContextLoaderListender.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class)) {
			
		}
		logger.info("Initialized Application Context!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
