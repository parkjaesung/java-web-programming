package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IOUtils {
	public static byte[] convertInputStreamToByte(String path) throws IOException {
		byte[] fileContent = null;
		File file = new File(path);
		try (FileInputStream fin = new FileInputStream(file)) {
	        fileContent = new byte[(int)file.length()];
	        fin.read(fileContent);
		} 
		return fileContent;
	}
}
