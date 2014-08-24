package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;

public class ListController implements Controller {
	private QuestionDao questionDao = new QuestionDao();
	private List<Question> questions;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		questions = questionDao.findAll();
		request.setAttribute("questions", questions);
		return "list.jsp";
	}
}
