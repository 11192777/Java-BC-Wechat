package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import po.WcFriend;
import po.WcUser;
import wc.panel.MyJLabel;

public class GUIUtil {

	public static final Font SONG_20 = new Font("ËÎÌå", Font.PLAIN, 20);
	public static final Font SONG_13 = new Font("ËÎÌå", Font.PLAIN, 13);
	public static final Font FANGSONG_20 = new Font("·ÂËÎ", Font.BOLD, 20);
	public static final Font FANGSONG_13 = new Font("·ÂËÎ", Font.BOLD, 13);
	public static final Font XINGKAI = new Font("»ªÎÄÐÐ¿¬", Font.PLAIN, 16);
	public static final Font FANGSONG_14 = new Font("·ÂËÎ", Font.BOLD, 14);

	public static void setButton(JButton b, ImageIcon ico, String hint, int x, int y, int width, int height) {
		b.setFocusable(false);
		b.setBounds(x, y, width, height);
		b.setIcon(ico);
		b.setBorder(null);
		b.setToolTipText(hint);
		b.setVerticalTextPosition(JButton.BOTTOM);
		b.setHorizontalTextPosition(JButton.CENTER);
	}

	public static void useSkin() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JScrollPane setJScrollPane(JPanel panel, int x, int y, int width, int height) {
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(x, y, width, height);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		return scrollPane;
	}

	public static void setLable(JLabel j, String text, Color c, int x, int y, int width, int height) {
		j.setText(text);
		j.setForeground(c);
		j.setFont(GUIUtil.FANGSONG_13);
		j.setBounds(x, y, width, height);
	}

	public static void setMyJlabel(MyJLabel j, WcFriend w, String text, ImageIcon head) {
		j.w = w;
		j.setText(text);
		j.setPreferredSize(new Dimension(245, 60));
		j.setIcon(head);
		j.setFont(GUIUtil.XINGKAI);
		j.setBackground(ColorUtil.GRAY_233);
		j.setOpaque(true);
	}

	public static void setJLabel(JLabel j, ImageIcon jpg, int x, int y, int width, int height) {
		j.setFont(GUIUtil.XINGKAI);
		j.setBounds(x, y, width, height);
		j.setIcon(jpg);
	}

	public static void sleepMoment(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setPersonLabel(JLabel j, Font f, int x, int y, int width, int height) {
		j.setOpaque(false);
		j.setFont(f);
		j.setBounds(x, y, width, height);
	}

	public static void setTextField(JTextField t, int x, int y, int width, int height) {
		t.setOpaque(false);
		t.setBorder(null);
		t.setFont(GUIUtil.SONG_13);
		t.setBounds(x, y, width, height);
	}
}
