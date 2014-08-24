package webserver;

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
		
		try (InputStream is = connection.getInputStream(); 
			 OutputStream os = connection.getOutputStream()) {
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
