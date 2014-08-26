package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.Controller;
import core.utils.ApplicationContextUtils;

public class ShowController implements Controller {
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = ApplicationContextUtils.getBean(request, QuestionDao.class);
		AnswerDao answerDao = ApplicationContextUtils.getBean(request, AnswerDao.class);
		
		long questionId = Long.parseLong(request.getParameter("questionId"));
		Question question;
		List<Answer> answers;
		question = questionDao.findById(questionId);
		answers = answerDao.findAllByQuestionId(questionId);
		request.setAttribute("question", question);
		request.setAttribute("answers", answers);
		
		return "show.jsp";
	}
}
