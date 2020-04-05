package server;

import java.net.Socket;

import control.SocketReader;
import control.SocketWriter;
import po.WcUser;

public class Client {
	public boolean haveLogin;
	public boolean haveOnline;
	public WcUser user;
	public Socket socket;
	public SocketReader reader;
	public SocketWriter writer;

	public Client(Socket socket) {
		this.haveLogin = false;
		this.haveLogin = false;
		this.user = null;
		this.socket = socket;
		this.writer = new SocketWriter(socket);
		this.reader = new SocketReader(socket, this);
		this.reader.start();
	}

}
