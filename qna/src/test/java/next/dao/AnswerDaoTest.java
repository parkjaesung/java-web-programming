package next.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import next.model.Answer;
import next.support.db.ConnectionManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class AnswerDaoTest {
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	public void crud() throws Exception {
		long questionId = 1L;
		Answer expected = new Answer("javajigi", "answer contents", questionId);
		AnswerDao dut = new AnswerDao();
		dut.insert(expected);
		
		List<Answer> answers = dut.findAllByQuestionId(questionId);
		assertTrue(answers.size() > 0);
	}
}
