package next.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import next.config.Config;
import next.model.Question;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QuestionDaoTest {
	private QuestionDao questionDao;
	
	@Before
	public void setup() {
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class)) {
			questionDao = ac.getBean(QuestionDao.class);
		}
	}

	@Test
	public void crud() throws Exception {
		Question expected = new Question("자바지기", "title", "contents");
		questionDao.insert(expected);
		
		List<Question> questions = questionDao.findAll();
		assertTrue(questions.size() > 0);
	}
}
