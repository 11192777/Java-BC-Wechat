package wc.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

import javax.swing.JTextField;

import com.sun.security.ntlm.Client;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import io.Remark;
import po.WcFriend;
import po.WcFriendRequest;
import util.Agreements;
import util.ChineseCharToEn;
import wc.frame.MainFrame;

public class JtextFieldKeyListener implements KeyListener {

	private static final String DEFAULT_TEXT = "µã»÷±à¼­±¸×¢...";
	private JTextField text = null;
	private WcFriend w = null;

	public JtextFieldKeyListener(JTextField text) {
		this.text = text;
	}

	public void setUser(WcFriend w) {
		this.w = w;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) {
			text.setFocusable(false);
			if (!text.getText().equals("")) {
				if (text.getText().length() > 8) {
					w.remark = text.getText().substring(0, 8);
				} else {
					w.remark = text.getText();
				}
				w.tail.label.setText(w.remark);
				MainFrame.client.writer.writeRemark(
						new Remark(MainFrame.client.user.getId(), w.tail.getId(), w.dialogue, text.getText()));
			} else {
				w.remark = null;
				MainFrame.client.writer
						.writeRemark(new Remark(MainFrame.client.user.getId(), w.tail.getId(), w.dialogue, null));
				text.setText(DEFAULT_TEXT);
				w.tail.label.setText(w.tail.getName());
			}
			if (w.remark != null) {
				w.index = (int) ChineseCharToEn.getFirstLetter(w.remark).toCharArray()[0];
			}else {
				w.index = (int) ChineseCharToEn.getFirstLetter(w.tail.getName()).toCharArray()[0];
			}
			java.util.Collections.sort(MainFrame.client.user.friendList);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
