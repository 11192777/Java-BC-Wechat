package wc.panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import po.WcFriend;
import util.Agreements;
import util.ColorUtil;
import util.GUIUtil;
import wc.frame.MainFrame;
import wc.listener.JtextFieldKeyListener;
import wc.listener.JtextFieldMouseListener;

public class PersonalPanel extends JLayeredPane {
	public static PersonalPanel instance = new PersonalPanel();

	private JLabel name = new JLabel();
	private JLabel location = new JLabel();
	private JLabel id = new JLabel();
	private JTextField remark = new JTextField();
	private JLabel back = new JLabel();
	private JLabel head = new JLabel();
	private JLabel signature = new JLabel();
	private JtextFieldKeyListener keyListener = null;
	private JtextFieldMouseListener mouselistener = null;
	private ImageIcon men = new ImageIcon("images/men.jpg");
	private ImageIcon women = new ImageIcon("images/women.jpg");
	private JButton send = new JButton("发送消息");
	private WcFriend w = null;

	private PersonalPanel() {
		this.setLayout(null);
		this.setBackground(ColorUtil.GRAY_245);
		this.setBounds(310, 60, 670, 400);
		this.setVisible(false);
		this.setElement();
	}

	public void setElement() {
		this.keyListener = new JtextFieldKeyListener(remark);
		this.mouselistener = new JtextFieldMouseListener(remark);
		back.setIcon(new ImageIcon("images/perBack.jpg"));
		back.setBounds(0, 0, 670, 400);

		GUIUtil.setPersonLabel(head, null, 458, 48, 60, 60);
		GUIUtil.setPersonLabel(name, new Font("黑体", Font.BOLD, 25), 140, 50, 300, 50);
		GUIUtil.setPersonLabel(signature, new Font("仿宋", Font.PLAIN, 16), 140, 100, 330, 20);
		GUIUtil.setTextField(remark, 230, 188, 150, 20);
		GUIUtil.setPersonLabel(location, GUIUtil.XINGKAI, 230, 252, 150, 20);
		GUIUtil.setPersonLabel(id, GUIUtil.XINGKAI, 230, 220, 150, 20);

		signature.setForeground(Color.black);

		remark.setFont(GUIUtil.XINGKAI);
		remark.setOpaque(false);
		remark.addKeyListener(keyListener);
		remark.addMouseListener(mouselistener);

		send.setBounds(260, 320, 130, 30);
		send.setFocusable(false);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean have = false;
				BookListPanel.instance.dialogueClick = BookListPanel.instance.bookClick;
				for (WcFriend f : BookListPanel.instance.dialogue) {
					if (f.equals(w)) {
						have = true;
						break;
					}
				}
				if (!have) {
					w.dialogue = 1;
					BookListPanel.instance.dialogue.add(w);
					MainFrame.client.writer.writeString(Agreements.ADD_DIALOGUE, w.tail.getId());
				}
				MainFrame.instance.setMessages(w.tail.mess);
				MessageTextPanel.instance.sendState(w.tail);
				ChosePanel.instance.message.doClick();
			}
		});
		this.add(back, JLayeredPane.DEFAULT_LAYER);
		this.add(send, JLayeredPane.PALETTE_LAYER);
		this.add(name, JLayeredPane.PALETTE_LAYER);
		this.add(id, JLayeredPane.PALETTE_LAYER);
		this.add(remark, JLayeredPane.PALETTE_LAYER);
		this.add(location, JLayeredPane.PALETTE_LAYER);
		this.add(head, JLayeredPane.PALETTE_LAYER);
		this.add(signature, JLayeredPane.PALETTE_LAYER);
		this.add(NewFriendPanel.instance, JLayeredPane.MODAL_LAYER);
	}

	public void setLable(WcFriend w) {
		this.w = w;
		keyListener.setUser(w);
		mouselistener.setWcFriend(w);
		name.setText(w.tail.getName());
		if (w.tail.getSex().equals("男")) {
			name.setIcon(men);
		} else {
			name.setIcon(women);
		}
		head.setIcon(new ImageIcon(w.tail.head.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		remark.setFocusable(false);
		if (w.remark == null) {
			remark.setText("点击编辑备注...");
		} else {
			remark.setText(w.remark);
		}
		location.setText(w.tail.getLocation());
		id.setText(w.tail.getId());
		signature.setText(w.tail.getSignature());

		this.setVisible(true);
		this.revalidate();
	}
}
