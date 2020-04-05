package wc.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import po.WcFriend;
import po.WcFriendRequest;
import po.WcUser;
import util.Agreements;
import util.ChineseCharToEn;
import util.ColorUtil;
import util.GUIUtil;
import wc.frame.MainFrame;

public class NewFriendPanel extends JPanel implements ActionListener {

	public static NewFriendPanel instance = new NewFriendPanel();
	public static ArrayList<UserButton> newFirendsList = new ArrayList<UserButton>();

	private JPanel newFriends = new JPanel();
	private JScrollPane friendsList = null;
	private JTextField searchText = new JTextField();
	private JButton searchButton = new JButton("搜索");

	public NewFriendPanel() {
		this.setVisible(false);
		this.setLayout(null);
		this.setBounds(0, 0, 670, 400);
		this.setElement();
	}

	public void setElement() {
		this.searchText.setBounds(5, 20, 200, 25);
		this.searchButton.setBounds(215, 22, 60, 20);
		this.searchButton.addActionListener(this);

		this.add(searchButton);
		this.add(searchText);

		this.newFriends.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.newFriends.setVisible(true);

		this.friendsList = GUIUtil.setJScrollPane(newFriends, 0, 70, 668, 300);
		this.add(friendsList);
	}

	public void setAddSearch() {
		this.newFriends.removeAll();
		this.updateUI();
	}

	public void setNewFriends() {
		int size = 0;
		this.newFriends.removeAll();
		for (UserButton b : newFirendsList) {
			this.newFriends.add(b.userLabel);
			this.newFriends.add(b.add);
			this.newFriends.add(b.refuse);
			size++;
		}
		this.newFriends.setPreferredSize(new Dimension(310, size * 60));
		this.newFriends.updateUI();
	}

	public void setNewFriend(WcUser w) {
		UserButton u = new UserButton(w);
		u.add = new JButton("添加");
		u.add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (WcFriend f : MainFrame.client.user.friendList) {
					if (f.tail.getId().equals(w.getId())) {
						JOptionPane.showMessageDialog(null, "已拥该好友！");
						return;
					}
				}
				MainFrame.client.writer.writeRequest(new WcFriendRequest(MainFrame.client.user.getId(), w.getId()),
						Agreements.ADD_REQUEST);
				JOptionPane.showMessageDialog(null, "已拥发送添加请求！");
			}
		});
		this.newFriends.removeAll();
		this.newFriends.add(u.userLabel);
		this.newFriends.add(u.add);
		this.newFriends.setPreferredSize(new Dimension(310, 60));
		this.newFriends.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.searchButton) {
			if (MainFrame.client.user.getId().equals(this.searchText.getText())) {
				JOptionPane.showMessageDialog(null, "不能添加自己为好友！");
				return;
			}
			for (WcFriend wf : MainFrame.client.user.friendList) {
				if (wf.tail.getId().equals(this.searchText.getText())) {
					JOptionPane.showMessageDialog(null, "已拥有该好友！");
					return;
				}
			}
			System.out.println(searchText.getText());
			MainFrame.client.writer.writeString(Agreements.ADD_FRIENDS, this.searchText.getText());
		}
	}

	public void addUserButton(WcUser w) {
		newFirendsList.add(new UserButton(w));
		this.setNewFriends();
	}

	public class UserButton implements ActionListener {
		JButton add = null;
		JButton refuse = null;
		WcUser w;
		JLabel userLabel = new JLabel();

		public UserButton(WcUser w) {
			this.w = w;
			add = new JButton("接受");
			add.addActionListener(this);
			refuse = new JButton("拒绝");
			refuse.addActionListener(this);
			userLabel.setText(w.getName() + "     " + w.getId());
			userLabel.setPreferredSize(new Dimension(400, 60));
			userLabel.setIcon(w.head);
			setBackground(ColorUtil.GRAY_233);
			setOpaque(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton b = (JButton) e.getSource();
			if (b == this.add) {
				w.label = new MyJLabel();
				WcFriend f = new WcFriend(w, 0, null);
				GUIUtil.setMyJlabel(w.label, f, w.getName(), w.head);
				w.label.addMouseListener(BookListPanel.instance);
				w.mess = new Messages();
				f.index = (int) ChineseCharToEn.getFirstLetter(f.tail.getName()).toCharArray()[0];
				MainFrame.client.user.friendList.add(f);
				Collections.sort(MainFrame.client.user.friendList);
				BookListPanel.instance.setBookLisetPanel(MainFrame.client.user.friendList);
				newFirendsList.remove(this);
				setNewFriends();
				MainFrame.client.writer.writeRequest(new WcFriendRequest(w.getId(), MainFrame.client.user.getId()),
						Agreements.UPDATA_FRIENDS);
			} else {
				newFirendsList.remove(this);
				setNewFriends();
			}
			MainFrame.client.writer.writeRequest(new WcFriendRequest(w.getId(), MainFrame.client.user.getId()),
					Agreements.DELETE_REQUEST);
		}

	}
}
