package wc.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import po.WcFriend;
import util.Agreements;
import wc.frame.MainFrame;
import wc.panel.BookListPanel;
import wc.panel.MessageTextPanel;
import wc.panel.Messages;
import wc.panel.MyJLabel;

public class JMenuItemActionListener implements ActionListener {

	private JMenuItem item = null;
	private MyJLabel label = null;
	private boolean parten = true;

	public JMenuItemActionListener(JMenuItem item) {
		this.item = item;
	}

	public void setJLabelAndParten(MyJLabel label, boolean parten) {
		this.label = label;
		this.parten = parten;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(item)) {
			if (!this.parten) {
				removeFriend(MainFrame.client.user.friendList, this.label.w.tail.getId());
				BookListPanel.instance.setBookLisetPanel(MainFrame.client.user.friendList);
			}
			removeDialogue(BookListPanel.instance.dialogue, this.label.w.tail.getId());
			if (this.parten) {
				BookListPanel.instance.setDialoguePanel();
				BookListPanel.instance.updateUI();
			}
		}
	}

	public static void removeFriend(ArrayList<WcFriend> friends, String id) {
		for (WcFriend w : friends) {
			if (w.tail.getId().equals(id)) {
				friends.remove(w);
				MainFrame.client.writer.writeString(Agreements.DELETE_FRIEND, w.tail.getId());
				JOptionPane.showMessageDialog(null, "ÒÑÉ¾³ýºÃÓÑ¡£");
				break;
			}
		}
	}

	public static void removeDialogue(ArrayList<WcFriend> dialogue, String id) {
		for (WcFriend w : dialogue) {
			if (w.tail.getId().equals(id)) {
				MessageTextPanel.instance.setVisible(false);
				w.tail.mess.setVisible(false);
				MainFrame.instance.hint_label.setText("");
				MainFrame.client.writer.writeString(Agreements.DELETE_DIALOGUE, w.tail.getId());
				dialogue.remove(w);
				break;
			}
		}
	}
}
