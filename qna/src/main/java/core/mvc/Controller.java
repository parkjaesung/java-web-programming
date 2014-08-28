package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	public static final String DEFAULT_API_PREFIX = "api";
	
	Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
