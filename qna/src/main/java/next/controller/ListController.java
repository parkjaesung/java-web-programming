package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListController {
	@Autowired
	private QuestionDao questionDao;
	
	@RequestMapping("/list.next")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Question> questions;
		questions = questionDao.findAll();

		ModelAndView mav = new ModelAndView("list");
		mav.addObject("questions", questions);
		return mav;
	}
}
