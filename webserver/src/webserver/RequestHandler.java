package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler extends Thread {
	private Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	public void run() {
		System.out.println("New Client Connected!");
		
		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			// TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
			
			DataOutputStream dos = new DataOutputStream(out);
			byte[] body = "Hello World".getBytes();
			response200Header(dos, body.length);
			responseBody(dos, body);
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
