package next.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.Controller;
import core.utils.ApplicationContextUtils;

public class AddAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AnswerDao answerDao = ApplicationContextUtils.getBean(request, AnswerDao.class);
		QuestionDao questionDao = ApplicationContextUtils.getBean(request, QuestionDao.class);
		
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		Answer answer = new Answer(writer, contents, questionId);

		answerDao.insert(answer);
		questionDao.updateCommentCount(questionId);

		return Controller.DEFAULT_API_PREFIX;
	}

}
