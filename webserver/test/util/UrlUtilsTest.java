package util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UrlUtilsTest {
	@Test
	public void findUrl() throws Exception {
		String header = "GET /index.html HTTP/1.1";
		String actual = UrlUtils.findUrl(header);
		assertThat(actual, is("/index.html"));
	}
	
	@Test
	public void findUrlIsEmptyOrNull() throws Exception {
		String header = null;
		String actual = UrlUtils.findUrl(header);
		assertThat(actual, is(""));
		
		header = "";
		actual = UrlUtils.findUrl(header);
		assertThat(actual, is(""));
	}
}
