package core.nmvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import com.google.gson.Gson;

public class JsonView implements View {
	public static final String DEFAULT_JSON_PARAM_KEY = "JSON_PARAM";
	
	@Override
	public String getContentType() {
		return "application/json";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (model == null) {
			render(response, "");
			return;
		}
		
		Object data = model.get(DEFAULT_JSON_PARAM_KEY);
		if (data == null) {
			render(response, "");
			return;
		}
		Gson gson = new Gson();
		String json = gson.toJson(data);
		render(response, json);
	}

	private void render(HttpServletResponse response, String json)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
}
