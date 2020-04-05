package wc.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import po.WcFriend;

public class JtextFieldMouseListener implements MouseListener {

	private JTextField text = null;
	private WcFriend w = null;

	public JtextFieldMouseListener(JTextField text) {
		this.text = text;
	}

	public void setWcFriend(WcFriend w) {
		this.w = w;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		text.setFocusable(true);
		text.requestFocus();
		if (w.remark == null) {
			text.setText("");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
