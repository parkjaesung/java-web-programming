package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.springframework.beans.factory.annotation.Autowired;

import core.mvc.Controller;

public class SaveController implements Controller {
	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Question question = new Question(writer, title, contents);

		questionDao.insert(question);
		return "redirect:/list.next";
	}
}
