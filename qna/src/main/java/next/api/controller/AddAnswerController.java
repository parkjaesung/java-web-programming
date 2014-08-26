package next.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.model.Answer;
import core.mvc.Controller;

public class AddAnswerController implements Controller {

	AnswerDao dao = new AnswerDao();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		Answer answer = new Answer(writer, contents, questionId);

		dao.insert(answer);

		return "api";
	}

}
