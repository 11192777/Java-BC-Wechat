package wc.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import po.WcFriend;
import po.WcGroup;
import util.ColorUtil;
import wc.frame.MainFrame;
import wc.listener.JMenuItemActionListener;
import wc.listener.JMenuItemListener;

public class BookListPanel extends JPanel implements MouseListener {
	public static BookListPanel instance = new BookListPanel();

	private JLabel[] index_label = new JLabel[29];
	private boolean pattern = true;
	public MyJLabel bookClick = null;
	public MyJLabel dialogueClick = null;
	private JMenuItemActionListener listener = null;
	public ArrayList<WcFriend> dialogue;
	public ArrayList<WcGroup> groups;
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem delete = new JMenuItem("É¾³ý");

	public BookListPanel() {
		this.setVisible(true);
		this.setBackground(ColorUtil.gray);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.jpm.add(delete);
		this.listener = new JMenuItemActionListener(delete);
		this.delete.addActionListener(this.listener);
		this.delete.addMouseListener(new JMenuItemListener(jpm));
		this.dialogue = new ArrayList<>();
		this.groups = new ArrayList<>();
		for (int i = 0; i < 29; i++) {
			this.index_label[i] = new JLabel();
			this.index_label[i].setIcon(new ImageIcon("index/" + (char) (i + 95) + ".jpg"));
		}
	}

	public void setBookLisetPanel(ArrayList<WcFriend> friends) {
		this.pattern = false;
		this.removeAll();
		int size = 0;
		int index = -1;
		for (WcFriend w : friends) {
			if (w.index != index) {
				this.add(this.index_label[w.index - 95]);
				index = w.index;
				size++;
			}
			this.add(w.tail.label);
		}
		this.setPreferredSize(new Dimension(250, friends.size() * 60 + size * 36));
		this.updateUI();
	}

	public void searchBookList(ArrayList<WcFriend> friends, String name) {
		this.pattern = false;
		this.removeAll();
		int size = 0;
		for (WcFriend w : friends) {
			if (w.remark != null) {
				if (w.remark.contains(name)) {
					size++;
					this.add(w.tail.label);
				}
				continue;
			}
			if (w.tail.getName().contains(name)) {
				size++;
				this.add(w.tail.label);
			}
		}
		this.setPreferredSize(new Dimension(250, size * 60));
		this.updateUI();
	}

	public void setDialoguePanel() {
		this.pattern = true;
		this.removeAll();
		int size = 0;
		for (WcFriend w : dialogue) {
			this.add(w.tail.label);
			size++;
		}
		this.setPreferredSize(new Dimension(250, size * 60));
		this.updateUI();

	}
	
	public void setGroupsPanel () {
		this.removeAll();
		int size = 0;
		for (WcGroup p : groups) {
			this.add(p.label);
			size++;
		}
		this.setPreferredSize(new Dimension(250, size * 60));
		this.updateUI();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		MyJLabel j = (MyJLabel) e.getSource();
		if (e.getButton() == MouseEvent.BUTTON3) {
			jpm.show(j, e.getX(), e.getY());
			this.listener.setJLabelAndParten(j, this.pattern);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		MyJLabel j = (MyJLabel) e.getSource();
		if (!this.pattern) {
			if (this.bookClick != null) {
				this.bookClick.setBackground(ColorUtil.GRAY_233);
			}
			if (!j.w.tail.getId().equals("")) {
				NewFriendPanel.instance.setVisible(false);
				this.bookClick = j;
				PersonalPanel.instance.setLable(j.w);
			} else {
				PersonalPanel.instance.setVisible(true);
				NewFriendPanel.instance.setVisible(true);
				NewFriendPanel.instance.setNewFriends();
			}
		} else {
			if (this.dialogueClick != null) {
				this.dialogueClick.setBackground(ColorUtil.GRAY_233);
			}
			this.dialogueClick = j;
			MainFrame.instance.setMessages(j.w.tail.mess);
			MessageTextPanel.instance.setVisible(true);
			MessageTextPanel.instance.sendState(j.w.tail);
		}
		MainFrame.instance.hint_label.setText(j.w.remark == null ? j.w.tail.getName() : j.w.remark);
		j.setBackground(ColorUtil.GRAY_195);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		MyJLabel j = (MyJLabel) e.getSource();
		if (j != this.bookClick && j != this.dialogueClick) {
			j.setBackground(ColorUtil.GRAY_214);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		MyJLabel j = (MyJLabel) e.getSource();
		if (j != this.bookClick && j != this.dialogueClick) {
			j.setBackground(ColorUtil.GRAY_233);
		}
	}
}
