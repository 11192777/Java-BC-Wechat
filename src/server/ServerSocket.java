package server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocket {
	public static Socket client = null;

	public ServerSocket() {

	}

	public void setSocket (String path, int port) {
		try {
			ServerSocket.client = new Socket(path, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
