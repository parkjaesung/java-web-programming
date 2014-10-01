package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import util.IOUtils;
import util.PathUtils;
import util.UrlUtils;

public class RequestHandler extends Thread {
	private Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	public void run() {
		System.out.println("New Client Connected!");
		
		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();
			
			byte fileContent[] = null;
			if(!"".equals(line)) {
				System.out.println(line);
				String fileName = UrlUtils.findUrl(line);
				String fullPath = PathUtils.getCurrentPath() + File.separator + "webapp" + fileName;
				System.out.println("path : " + fullPath);
				
				fileContent = IOUtils.convertInputStreamToByte(fullPath);
			}
			
			DataOutputStream dos = new DataOutputStream(out);
			response200Header(dos, fileContent.length);
			responseBody(dos, fileContent);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void response200Header(DataOutputStream dos, int lengthOfBytes) {
		try {
			dos.writeBytes("HTTP/1.0 200 Document Follows \r\n");
			dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
			dos.writeBytes("Content-Length: " + lengthOfBytes + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void responseBody(DataOutputStream dos, byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.writeBytes("\r\n");
			dos.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
