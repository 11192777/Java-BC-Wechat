package startup;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import server.Client;
import wc.frame.LoginFrame;
import wc.frame.MainFrame;

public class Main {
	public static String DEFAULTIP = "127.0.0.1";
	public static int DEFAULTPORT = 5929;

	public static void main(String[] args) {
		LoginFrame.instance.setVisible(true);
		Socket socket = null;
		try {
			socket = new Socket(DEFAULTIP, DEFAULTPORT);
			if (socket != null) {
				MainFrame.client = new Client(socket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "连接不到服务器！");
		}
	}
}
