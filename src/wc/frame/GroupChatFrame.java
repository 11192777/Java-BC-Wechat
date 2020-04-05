package wc.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import io.Message;
import po.WcFriend;
import po.WcGroup;
import po.WcGroupMember;
import util.Agreements;
import wc.panel.Messages;

public class GroupChatFrame extends JFrame implements ActionListener {
	private WcGroup wg;
	public Messages mess = new Messages();
	private JTextArea list = new JTextArea();
	private JTextArea area = new JTextArea();
	private JButton send = new JButton("发送");
	private JButton invite = new JButton("邀请新成员");
	private JButton delete = new JButton("删除群成员");
	private JButton change = new JButton("修改群名字");
	public ArrayList<WcGroupMember> members;

	public GroupChatFrame(WcGroup p) {
		this.wg = p;
		this.setSize(900, 590);
		this.setLayout(null);
		this.setLocationRelativeTo(getOwner());
		this.setElements();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}

	public void setElements() {
		this.mess.setBounds(0, 0, 670, 400);
		this.mess.setVisible(true);
		this.list.setBounds(671, 151, 230, 550);
		this.list.setVisible(true);
		this.list.setFont(new Font("仿宋", Font.BOLD, 16));
		this.list.setEditable(false);
		this.area.setBounds(0, 402, 670, 100);
		this.area.setLineWrap(true);
		this.area.setFont(new Font("仿宋", Font.BOLD, 16));
		this.send.setBounds(600, 510, 60, 30);
		this.send.setFocusable(false);
		this.send.addActionListener(this);
		this.invite.setBounds(710, 15, 140, 30);
		this.invite.addActionListener(this);
		this.invite.setFocusable(false);
		this.delete.setBounds(710, 55, 140, 30);
		this.delete.setFocusable(false);
		this.delete.addActionListener(this);
		this.change.setBounds(710, 95, 140, 30);
		this.change.addActionListener(this);
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
		this.add(area);
		this.add(list);
		this.add(send);
		this.add(invite);
		this.add(delete);
		this.add(mess);
		this.add(change);
	}

	public void setMembersList() {
		this.list.setText("");
		this.list.append("    群  主:" + wg.getHostId() + "\n");
		for (int i = 0; i < this.members.size(); i++) {
			if (members.get(i).getTailId().equals(wg.getHostId())) {
				continue;
			}
			this.list.append("    群成员:" + members.get(i).getTailId() + "\n");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.send) {
			mess.insertString("from  me:" + area.getText() + "\n");
			MainFrame.client.writer.writeMessage(
					new Message(MainFrame.client.user.getId(), wg.getGroupId(), area.getText()),
					Agreements.SEND_GROUP_MESSAGE);
			area.setText("");
		} else if (b == this.invite) {
			if (!MainFrame.client.user.getId().equals(wg.getHostId())) {
				JOptionPane.showMessageDialog(null, "没有权限！");
				return;
			}
			String name = JOptionPane.showInputDialog(null, "帐号：", "邀请群成员", 3);
			for (WcGroupMember m : members) {
				if (m.getTailId().equals(name)) {
					JOptionPane.showMessageDialog(null, "该成员已在群！");
					return;
				}
			}
			for (WcFriend f : MainFrame.client.user.friendList) {
				if (f.tail.getId().equals(name)) {
					JOptionPane.showMessageDialog(null, "添加成功！");
					members.add(new WcGroupMember(wg.getGroupId(), f.tail.getId()));
					MainFrame.client.writer.writeGroupMember(new WcGroupMember(wg.getGroupId(), name),
							Agreements.GROUP_INVITE);
					this.setMembersList();
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "未查到该好友！");
		} else if (b == this.delete) {
			if (!MainFrame.client.user.getId().equals(wg.getHostId())) {
				JOptionPane.showMessageDialog(null, "没有权限！");
				return;
			}
			String name = JOptionPane.showInputDialog(null, "帐号：", "删除群成员", 3);
			for (WcFriend f : MainFrame.client.user.friendList) {
				if (f.tail.getId().equals(name)) {
					MainFrame.client.writer.writeGroupMember(new WcGroupMember(wg.getGroupId(), name),
							Agreements.GROUP_DELETE);
					JOptionPane.showMessageDialog(null, "删除成功！");
					for (WcGroupMember m : members) {
						if (m.getTailId().equals(name)) {
							members.remove(m);
							this.setMembersList();
							return;
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "未查到该成员！");
		} else if (b == this.change) {
			if (!this.wg.getHostId().equals(MainFrame.client.user.getId())) {
				JOptionPane.showMessageDialog(null, "没有修改权限!");
				return;
			}
			String name = JOptionPane.showInputDialog(null, "名字：", "修改群名字", 3);
			if (name.length() > 8) {
				name = name.substring(0, 8);
			}
			this.wg.setGroupName(name);
			JOptionPane.showMessageDialog(null, "修改成功!");
			MainFrame.client.writer.writeMessage(new Message(this.wg.getGroupId(), name, null),
					Agreements.CHANGE_GROUP_NAME);
		}

	}
}
