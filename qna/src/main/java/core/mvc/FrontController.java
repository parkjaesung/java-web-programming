package core.mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
	
	private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
	private static final String DEFAULT_API_PREFIX = "api";
	
	private RequestMapping rm;

	@Override
	public void init() throws ServletException {
		rm = (RequestMapping)getServletContext().getAttribute(ServletContextLoader.DEFAULT_REQUEST_MAPPING);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);
		Controller controller = rm.findController(urlExceptParameter(requestUri));
		Object viewName;
		try {
			viewName = controller.execute(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
		movePage(req, resp, viewName);
	}

	void movePage(HttpServletRequest req, HttpServletResponse resp,
			Object viewName) throws ServletException, IOException {
		
		if(viewName instanceof String) {
			String str = (String)viewName;
			if (str.startsWith(DEFAULT_API_PREFIX)) {
				return;
			}
			
			if (str.startsWith(DEFAULT_REDIRECT_PREFIX)) {
				resp.sendRedirect(str.substring(DEFAULT_REDIRECT_PREFIX.length()));
				return;
			}
			
			RequestDispatcher rd = req.getRequestDispatcher(str);
			rd.forward(req, resp);
		} else {
			Gson gson = new Gson();
			String json = gson.toJson(viewName);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw = resp.getWriter();
			pw.write(json);
			pw.close();
		}
	}
	
	String urlExceptParameter(String forwardUrl) {
		int index = forwardUrl.indexOf("?");
		if (index > 0) {
			return forwardUrl.substring(0, index);
		}
		
		return forwardUrl;
	}
}
