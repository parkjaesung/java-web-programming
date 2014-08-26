package next.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import next.config.Config;
import next.model.Answer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnswerDaoTest {
	private AnswerDao answerDao;
	
	@Before
	public void setup() {
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class)) {
			answerDao = ac.getBean(AnswerDao.class);
		}
	}

	@Test
	public void crud() throws Exception {
		long questionId = 1L;
		Answer expected = new Answer("javajigi", "answer contents", questionId);
		answerDao.insert(expected);
		
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
		assertTrue(answers.size() > 0);
	}
}
