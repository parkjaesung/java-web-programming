package next.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.model.Answer;
import core.mvc.Controller;
import core.utils.ApplicationContextUtils;

public class AddAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AnswerDao dao = ApplicationContextUtils.getBean(request, AnswerDao.class);
		
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		Answer answer = new Answer(writer, contents, questionId);

		dao.insert(answer);

		return Controller.DEFAULT_API_PREFIX;
	}

}
