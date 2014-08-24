package core.mvc;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class FrontControllerTest {
	private FrontController frontController;
	
	@Before
	public void setup() {
		frontController = new FrontController();
	}

	@Test
	public void movePage() throws Exception {
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse resp = new MockHttpServletResponse();
		String viewName = "index";
		frontController.movePage(req, resp, viewName);
	}
	
	@Test
	public void urlExceptParameter() throws Exception {
		assertThat(frontController.urlExceptParameter("/show.next"), is("/show.next"));
		assertThat(frontController.urlExceptParameter("/show.next?id=2"), is("/show.next"));
	}
}
