package next.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.model.Answer;

import org.springframework.beans.factory.annotation.Autowired;

import core.mvc.Controller;

public class AddAnswerController implements Controller {
	@Autowired
	private AnswerDao answerDao;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		Answer answer = new Answer(writer, contents, questionId);

		answerDao.insert(answer);

		return Controller.DEFAULT_API_PREFIX;
	}

}
