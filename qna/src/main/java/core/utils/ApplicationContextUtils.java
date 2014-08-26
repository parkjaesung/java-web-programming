package core.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationContextUtils {
	public static <T> T getBean(HttpServletRequest request, Class<T> requiredType) {
		ServletContext sc = ServletContextUtils.getServletContext(request);
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		return wac.getBean(requiredType);
	}
}
