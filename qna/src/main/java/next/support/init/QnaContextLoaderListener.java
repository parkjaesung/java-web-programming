package next.support.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import next.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class QnaContextLoaderListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory
			.getLogger(QnaContextLoaderListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(Config.class);
		ac.refresh();
		sce.getServletContext().setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				ac);
		logger.info("Initialized Application Context!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
