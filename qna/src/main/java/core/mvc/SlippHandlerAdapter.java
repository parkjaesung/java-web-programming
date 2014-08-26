package core.mvc;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.LastModified;

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
            throw new IllegalStateException("Controller 형식이 맞는지 확인해 주세요.");
        }
        
        ModelAndView mav = new ModelAndView((String)result);
        Enumeration<String> names = request.getAttributeNames();
        
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            mav.addObject(name, request.getAttribute(name));
        }
        return mav;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        if (handler instanceof LastModified) {
            return ((LastModified) handler).getLastModified(request);
        }
        return -1L;
    }
}
