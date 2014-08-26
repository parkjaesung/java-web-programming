package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import core.utils.ApplicationContextUtils;

public class ListController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = ApplicationContextUtils.getBean(request, QuestionDao.class);

		List<Question> questions;
		questions = questionDao.findAll();

		ModelAndView mav = new ModelAndView("list");
		mav.addObject("questions", questions);
		return mav;
	}
}
