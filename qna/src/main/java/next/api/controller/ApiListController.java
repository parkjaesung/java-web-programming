package next.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;

public class ApiListController implements Controller {
	private QuestionDao questionDao = new QuestionDao();
	
	
	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Question> questions;
		questions = questionDao.findAll();
		
		return questions;
	}
}
