package wc.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import server.Client;
import startup.Main;

public class SettingFrame extends JFrame {
	public static SettingFrame instance = null;

	private JLabel ip = new JLabel("ServerIp:");
	private JTextField ipText = new JTextField();
	private JLabel port = new JLabel("端   口 :");
	private JTextField portText = new JTextField();
	private JButton save = new JButton("保存");

	public SettingFrame() {
		this.setSize(300, 200);
		this.setLayout(null);
		this.setLocationRelativeTo(getOwner());
		this.setElements();
		this.setVisible(true);
	}

	public void setElements() {
		ip.setBounds(50, 10, 80, 30);
		ipText.setBounds(120, 10, 100, 30);
		ipText.setText(Main.DEFAULTIP);
		port.setBounds(50, 50, 80, 30);
		portText.setBounds(120, 50, 100, 30);
		portText.setText(new Integer(Main.DEFAULTPORT).toString());
		save.setBounds(115, 100, 70, 30);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Socket socket = null;;
				try {
					socket = new Socket(ipText.getText(),Integer.valueOf(portText.getText(), 10));
					MainFrame.client = new Client(socket);
					JOptionPane.showMessageDialog(null, "配置成功！");
					SettingFrame.instance.setVisible(false);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "端口输入错误！");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "连接不到！");
				}
			}
		});
		this.add(ip);
		this.add(ipText);
		this.add(port);
		this.add(portText);
		this.add(save);
	}
}
