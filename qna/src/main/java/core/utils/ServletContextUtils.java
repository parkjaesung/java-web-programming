package core.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class ServletContextUtils {
	public static ServletContext getServletContext(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		
		return request.getSession().getServletContext();
	}
}
