package wc.panel;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Messages extends JPanel {
	private JScrollPane scrollPane = null;
	private JTextPane textpanel = null;
	private SimpleAttributeSet attrSet = new SimpleAttributeSet();
	private StyledDocument doc = null;

	public Messages() {

		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.black);
		StyleConstants.setFontFamily(attrSet, "黑体");
		StyleConstants.setBold(attrSet, false);
		StyleConstants.setItalic(attrSet, false);
		StyleConstants.setFontSize(attrSet, 16);
		StyleConstants.setForeground(attrSet, new Color(0, 0, 0));
		StyleConstants.setAlignment(attrSet, StyleConstants.ALIGN_RIGHT);
		this.setBounds(310, 58, 670, 400);
		textpanel = new JTextPane();
		textpanel.setEditable(false);
		doc = textpanel.getStyledDocument();
		scrollPane = new JScrollPane(textpanel);
		scrollPane.setBounds(0, 0, 670, 400);
		scrollPane.setBorder(null);
		this.add(scrollPane);
	}

	public void insertIcon(ImageIcon jpg) {
		ImageIcon tmp = new ImageIcon();
		tmp.setImage(jpg.getImage().getScaledInstance(33, 33, Image.SCALE_DEFAULT));
		textpanel.setCaretPosition(doc.getLength());
		textpanel.insertIcon(tmp);
	}

	public void insert(String text, ImageIcon img, boolean sig) {
		try { // 插入文本
			if (!sig) {
				this.insertIcon(img);
				doc.insertString(doc.getLength(), this.sendMessageFormat(text), attrSet);
			} else {
				String s[] = this.getMessafeFormat(text);
				doc.insertString(doc.getLength(), s[0], attrSet);
				this.insertIcon(img);
				if (s[1] != null) {
					doc.insertString(doc.getLength(), s[1], attrSet);
				} else {
					doc.insertString(doc.getLength(), "\n\n\n", attrSet);
				}
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		this.revalidate();
	}
	
	public void insertString (String text) {
		try {
			doc.insertString(doc.getLength(), text, attrSet);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String sendMessageFormat(String s) {
		StringBuffer sb = new StringBuffer();
		char[] arr = s.toCharArray();
		int size = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 255) {
				size++;
			} else {
				size += 2;
			}
			sb.append(arr[i]);
			if (size > 50) {
				size = 0;
				sb.append("\n" + "    ");
			}
		}
		sb.append("\n\n\n");
		return sb.toString();
	}

	public String[] getMessafeFormat(String s) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		char[] arr = s.toCharArray();
		int first = s.getBytes().length;
		int i = 0;
		int size = 0;
		if (first <= 50) {
			for (i = 0; i < 74 - first; i++) {
				sb.append(" ");
			}
			for (i = 0; i < arr.length; i++) {
				sb.append(arr[i]);
			}
			return new String[] { sb.toString(), null };
		} else {
			sb2.append("                      ");
			if (first % 2 == 1) {
				sb2.append(" ");
			}
			for (i = 0; i < arr.length; i++) {
				if (arr[i] < 255) {
					size++;
				} else {
					size += 2;
				}
				sb2.append(arr[i]);
				if (size > 50) {
					break;
				}
			}
		}
		sb.append("\n                      ");
		size = 0;
		for (; i < arr.length; i++) {
			if (arr[i] < 255) {
				size++;
			} else {
				size += 2;
			}
			sb.append(arr[i]);
			if (size > 50) {
				size = 0;
				sb.append("\n");
				sb.append("                      ");
			}
		}
		sb.append("\n\n\n");
		return new String[] { sb2.toString(), sb.toString() };
	}

}