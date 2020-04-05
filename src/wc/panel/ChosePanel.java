package wc.panel;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import po.WcFriend;
import util.ColorUtil;
import util.GUIUtil;
import wc.frame.FilePathSettingFrame;
import wc.frame.MainFrame;
import wc.frame.MyLetterFrame;

public class ChosePanel extends JPanel implements ActionListener {
	public static ChosePanel instance = null;
	public JButton head = new JButton();
	private ImageIcon image_head;
	public JButton message = new JButton();
	private ImageIcon[] image_message;
	private JButton friends = new JButton();
	private ImageIcon[] image_friends;
	private JButton collect = new JButton();
	private ImageIcon[] image_collect;
	private JButton setting = new JButton();

	public ChosePanel() {
		this.setBackground(ColorUtil.BLACK);
		this.setLayout(null);
		this.setBounds(0, 0, 60, 635);
		this.setButtons();
		this.setVisible(true);
	}

	private void setButtons() {
		this.setHead();
		image_message = new ImageIcon[] { new ImageIcon("images/message1.jpg"), new ImageIcon("images/message2.jpg") };
		image_friends = new ImageIcon[] { new ImageIcon("images/friends1.jpg"), new ImageIcon("images/friends2.jpg") };
		image_collect = new ImageIcon[] { new ImageIcon("images/collect1.jpg"), new ImageIcon("images/collect2.jpg") };
		image_head.setImage(image_head.getImage().getScaledInstance(34, 34, Image.SCALE_DEFAULT));
		GUIUtil.setButton(head, image_head, null, 13, 15, 34, 34);
		head.addActionListener(this);
		this.add(head);
		GUIUtil.setButton(message, image_message[0], "聊天", 0, 70, 60, 50);
		message.addActionListener(this);
		this.add(message);
		GUIUtil.setButton(friends, image_friends[0], "通讯录", 0, 120, 60, 50);
		friends.addActionListener(this);
		this.add(friends);
		GUIUtil.setButton(collect, image_collect[0], "收藏", 0, 170, 60, 50);
		collect.addActionListener(this);
		this.add(collect);
		GUIUtil.setButton(setting, new ImageIcon("images/choseSetting.jpg"), "设置", 0, 585, 60, 50);
		setting.addActionListener(this);
		this.add(setting);
	}

	public void setHead() {
		if (MainFrame.client.user != null) {
			this.image_head = MainFrame.client.user.head;
		} else {
			GUIUtil.sleepMoment(200);
			this.setHead();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.head) {
			MyLetterFrame.instance = new MyLetterFrame(MainFrame.client.user);
		} else if (b == this.message) {
			this.message.setIcon(this.image_message[1]);
			this.friends.setIcon(this.image_friends[0]);
			this.collect.setIcon(this.image_collect[0]);

			if (BookListPanel.instance.dialogueClick != null) {
				MessageTextPanel.instance.setVisible(true);
				MessageTextPanel.instance.area.requestFocus();
				MainFrame.instance.mess.setVisible(true);
				WcFriend w = BookListPanel.instance.dialogueClick.w;
				MainFrame.instance.hint_label.setText(w.remark == null ? w.tail.getName() : w.remark);
			}
			if (BookListPanel.instance.bookClick != null) {
				BookListPanel.instance.bookClick.setBackground(ColorUtil.GRAY_233);
			}
			if (BookListPanel.instance.dialogueClick != null) {
				BookListPanel.instance.dialogueClick.setBackground(ColorUtil.GRAY_195);
			}
			BookListPanel.instance.setDialoguePanel();
			PersonalPanel.instance.setVisible(false);
		} else if (b == this.friends) {
			this.message.setIcon(this.image_message[0]);
			this.friends.setIcon(this.image_friends[1]);
			this.collect.setIcon(this.image_collect[0]);

			if (BookListPanel.instance.dialogueClick != null) {
				BookListPanel.instance.dialogueClick.setBackground(ColorUtil.GRAY_233);
			}
			MainFrame.instance.hint_label.setText("");
			MessageTextPanel.instance.setVisible(false);
			MainFrame.instance.mess.setVisible(false);
			BookListPanel.instance.setBookLisetPanel(MainFrame.client.user.friendList);
		} else if (b == this.collect) {
			this.message.setIcon(this.image_message[0]);
			this.friends.setIcon(this.image_friends[0]);
			this.collect.setIcon(this.image_collect[1]);
			BookListPanel.instance.setGroupsPanel();
		} else if (b == this.setting) {
			FilePathSettingFrame.instance = new FilePathSettingFrame();
		}
	}

}
