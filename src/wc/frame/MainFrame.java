package wc.frame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import po.WcFriend;
import po.WcUser;
import server.Client;
import util.Agreements;
import util.ChineseCharToEn;
import util.ColorUtil;
import util.GUIUtil;
import wc.panel.BookListPanel;
import wc.panel.ChosePanel;
import wc.panel.MessageTextPanel;
import wc.panel.Messages;
import wc.panel.ModulePanel;
import wc.panel.MyJLabel;
import wc.panel.PersonalPanel;
import wc.panel.SearchPanel;

public class MainFrame extends JFrame implements MouseListener {

	public static MainFrame instance = null;
	public static Client client = null;
	static {
		GUIUtil.useSkin();
	}
	private Point location = null;
	public static JScrollPane books = null;
	public Messages mess = new Messages();

	private JButton close = new JButton();
	private ImageIcon[] close_image;
	private JButton hide = new JButton();
	private ImageIcon[] hide_images;
	public JLabel hint_label = new JLabel();

	public MainFrame() {
		this.setMove();
		this.setSize(980, 635);
		this.setUndecorated(true);
		this.setLayout(null);
		this.setBackground(ColorUtil.GRAY);
		this.setLocationRelativeTo(getOwner());
		this.addWindowListener(new closeWindow());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		com.sun.awt.AWTUtilities.setWindowShape(this,
				new RoundRectangle2D.Double(0.0D, 0.0D, this.getWidth(), this.getHeight(), 3.0D, 3.0D));
		this.setButtons();
		this.setPanels();
		this.setVisible(true);
	}

	private void setButtons() {
		close_image = new ImageIcon[] { new ImageIcon("images/close.jpg"), new ImageIcon("images/close2.jpg"),
				new ImageIcon("images/close3.jpg") };
		hide_images = new ImageIcon[] { new ImageIcon("images/hide1.jpg"), new ImageIcon("images/hide2.jpg"),
				new ImageIcon("images/hide3.jpg") };

		GUIUtil.setButton(close, close_image[0], "关闭", 946, 0, 35, 25);
		GUIUtil.setButton(hide, hide_images[0], "设置", 912, 0, 35, 25);
		GUIUtil.setLable(hint_label, "", Color.black, 340, 15, 500, 30);

		this.close.addMouseListener(this);
		this.hide.addMouseListener(this);
		this.hint_label.setFont(GUIUtil.FANGSONG_20);

		this.add(hint_label);
		this.add(close);
		this.add(hide);
	}

	private void setPanels() {
		MainFrame.books = GUIUtil.setJScrollPane(BookListPanel.instance, 62, 60, 248, 575);
		ChosePanel.instance = new ChosePanel();

		books.setBorder(null);
		this.setClient();
		this.mess.setVisible(false);
		this.add(mess);
		this.add(ChosePanel.instance);
		this.add(PersonalPanel.instance);
		this.add(MainFrame.books);
		this.add(ModulePanel.instance);
		this.add(SearchPanel.instance);
		this.add(MessageTextPanel.instance);
		ChosePanel.instance.message.doClick();
	}

	public void setMessages(Messages mess) {
		this.remove(this.mess);
		this.mess = mess;
		this.mess.setVisible(true);
		this.add(mess);
		this.mess.updateUI();
	}

	private void setClient() {
		while (MainFrame.client == null && MainFrame.client.user != null) {
			GUIUtil.sleepMoment(100);
			System.out.println("延迟等待...");
		}

		Collections.sort(MainFrame.client.user.friendList, new Comparator<WcFriend>() {
			@Override
			public int compare(WcFriend w1, WcFriend w2) {
				return w1.dialogue >= w2.dialogue ? 1 : -1;
			}
		});

		WcUser friend = new WcUser("", "", "新的朋友", "", "", "", "");
		friend.head = new ImageIcon("images/newFriends.jpg");
		MainFrame.client.user.friendList.add(new WcFriend(friend, 0, null, 95));
		for (WcFriend w : MainFrame.client.user.friendList) {
			w.tail.label = new MyJLabel();
			if (w.index == 0) {
				if (w.remark != null) {
					w.index = (int) ChineseCharToEn.getFirstLetter(w.remark).toCharArray()[0];
				}else {
					w.index = (int) ChineseCharToEn.getFirstLetter(w.tail.getName()).toCharArray()[0];
				}
				w.tail.mess = new Messages();
			}
			w.tail.label.addMouseListener(BookListPanel.instance);
			GUIUtil.setMyJlabel(w.tail.label, w, w.remark == null ? w.tail.getName() : w.remark, w.tail.head);
		}

		for (WcFriend w : MainFrame.client.user.friendList) {
			if (w.dialogue != 0) {
				BookListPanel.instance.dialogue.add(w);
			}
		}
		Collections.sort(MainFrame.client.user.friendList);
	}

	static class closeWindow extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			if (MainFrame.client != null) {
				MainFrame.client.writer.writeAgreement(Agreements.CLOSE);
				MainFrame.client.reader.close();
			}
		}
	}

	private void setMove() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				location = new Point(e.getX(), e.getY());
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (true) {
					Point temp = new Point(getLocation().x + e.getX() - location.x,
							getLocation().y + e.getY() - location.y);
					setLocation(temp);
				}
			}
		});
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			MainFrame.client.writer.writeAgreement(Agreements.CLOSE);
			System.exit(0);
		} else if (b == this.hide) {
			this.setExtendedState(JFrame.ICONIFIED);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			this.close.setIcon(this.close_image[1]);
		} else if (b == this.hide) {
			this.hide.setIcon(this.hide_images[1]);
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			this.close.setIcon(this.close_image[0]);
		} else {
			this.hide.setIcon(this.hide_images[0]);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			this.close.setIcon(this.close_image[2]);
		} else if (b == this.hide) {
			this.hide.setIcon(this.hide_images[2]);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
