package webserver;

import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String argv[]) throws Exception {
        // 서버소켓을 생성한다. 웹서버는 기본적으로 8080번 포트를 사용한다.
    	
    	try (ServerSocket listenSocket = new ServerSocket(8080)) {
            System.out.println("WebServer Socket Created");

            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
            	RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.start();
            }
    	}
    }
}
