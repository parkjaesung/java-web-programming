package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;
import core.utils.ApplicationContextUtils;

public class ListController implements Controller {
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = ApplicationContextUtils.getBean(request, QuestionDao.class);
		
		List<Question> questions;
		questions = questionDao.findAll();

		request.setAttribute("questions", questions);
		return "list.jsp";
	}
}
