package core.mvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextLoader implements ServletContextListener {
	public static final String DEFAULT_REQUEST_MAPPING = "DEFAULT_REQUEST_MAPPING";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		RequestMapping rm = new RequestMapping();
		rm.initMapping();
		sc.setAttribute(DEFAULT_REQUEST_MAPPING, rm);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}