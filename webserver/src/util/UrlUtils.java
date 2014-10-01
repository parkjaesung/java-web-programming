package util;

public class UrlUtils {

	public static String findUrl(String header) {
		if (header == null || "".equals(header)) {
			return "";
		}
		
		String[] tokens = header.split(" ");
		return tokens[1];
	}

}
