package wc.panel;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import io.Message;
import io.WriteFileTemp;
import po.WcUser;
import util.Agreements;
import util.ColorUtil;
import util.GUIUtil;
import wc.frame.MainFrame;

public class MessageTextPanel extends JPanel {

	public static MessageTextPanel instance = new MessageTextPanel();
	public static final int MAX_OPERATION = 3;
	public JTextArea area;
	private JButton send;
	private WcUser w = null;
	private JButton file = new JButton();
	private ExecutorService fixePool = Executors.newFixedThreadPool(MAX_OPERATION);

	public MessageTextPanel() {
		this.setVisible(false);
		this.setLayout(null);
		this.setElement();
		this.setBackground(Color.white);
		this.setBounds(310, 459, 670, 175);
	}

	public void setElement() {
		this.area = new JTextArea();
		this.area.setLineWrap(true);
		this.area.setFont(new Font("宋体", Font.BOLD, 14));
		this.send = new JButton("发送");
		GUIUtil.setButton(file, new ImageIcon("images/fileButton.jpg"), "发送文件", 0, 0, 35, 35);
		area.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == 10) {
					if (area.getText().equals("\r")) {
						area.setText("");
					} else if (!area.getText().equals("")) {
						send.doClick();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		area.setBounds(10, 35, 670, 95);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				w.label.setBackground(ColorUtil.GRAY_195);
				if (area.getText().length() > 200) {
					JOptionPane.showMessageDialog(null, "内容超过限制！");
				} else if (!area.getText().equals("")) {
					String text = area.getText().replace("\n", "");
					MainFrame.client.writer.writeMessage(new Message(MainFrame.client.user.getId(), w.getId(), text),Agreements.MESSAGE);
					w.mess.insert(text, MainFrame.client.user.head, true);
					area.setText("");
				}
			}
		});
		file.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileDialog fd = new FileDialog(new Frame(), "选择文件", FileDialog.LOAD);
				fd.setVisible(true);
				if (fd.getFile() != null) {
					WriteFileTemp wft = new WriteFileTemp(MainFrame.client.user.getId(), w.getId(), fd.getFile(),
							fd.getDirectory() + fd.getFile());
					fixePool.execute(wft);
				}
			}
		});
		send.setBounds(580, 140, 60, 30);
		this.add(area);
		this.add(file);
		this.add(send);
		this.updateUI();
	}

	public void sendState(WcUser w) {
		this.w = w;
		this.send.doClick();
	}

}
