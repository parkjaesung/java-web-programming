package core.mvc;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import core.nmvc.JsonView;

public class SlippHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        Object result = ((Controller) handler).execute(request, response);
        
        if (!(result instanceof String)) {
        	ModelAndView mav = new ModelAndView(new JsonView());
        	mav.addObject(JsonView.DEFAULT_JSON_PARAM_KEY, result);
            return mav;
        }
        
        String viewName = (String)result;
        
        if (viewName.startsWith(Controller.DEFAULT_API_PREFIX)) {
        	return new ModelAndView(new JsonView());
        }
        
        ModelAndView mav = new ModelAndView(viewName);
        Enumeration<String> names = request.getAttributeNames();
        
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            mav.addObject(name, request.getAttribute(name));
        }
        return mav;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1L;
    }
}
