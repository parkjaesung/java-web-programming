package core.mvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

public class RequestMappingTest {
	private RequestMapping rm;
	
	@Before
	public void setup() {
		rm = new RequestMapping();
	}
	
	@Test
	public void findController() throws Exception {
		String url = "index.next";
		Controller controller = new Controller() {
			@Override
			public String execute(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				return "index";
			}
		};
		rm.put(url, controller);
		
		Controller actual = rm.findController(url);
		assertThat(actual, is(controller));
	}
}
