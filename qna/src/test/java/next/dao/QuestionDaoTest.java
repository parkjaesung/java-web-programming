package next.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import next.model.Question;
import next.support.db.ConnectionManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class QuestionDaoTest {
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	public void crud() throws Exception {
		Question expected = new Question("자바지기", "title", "contents");
		QuestionDao dut = new QuestionDao();
		dut.insert(expected);
		
		List<Question> questions = dut.findAll();
		assertTrue(questions.size() > 0);
	}
}
