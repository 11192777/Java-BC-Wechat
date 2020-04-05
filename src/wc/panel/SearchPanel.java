package wc.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import util.ColorUtil;
import util.GUIUtil;
import wc.frame.MainFrame;

public class SearchPanel extends JLayeredPane {
	public static SearchPanel instance = new SearchPanel();
	private JLabel search_label = new JLabel();
	private JTextField search_text = new JTextField();
	private ImageIcon image_search[] = null;
	private JLabel search_shut = new JLabel();
	private ImageIcon image_shut[] = null;

	private SearchPanel() {
		this.setVisible(true);
		this.setLayout(null);
		this.setBounds(60, 0, 248, 60);
		this.setBackground(ColorUtil.GRAY_233);
		this.setElement();
	}

	public void setElement() {
		image_search = new ImageIcon[] { new ImageIcon("images/search.jpg"), new ImageIcon("images/search1.jpg") };
		image_shut = new ImageIcon[] {new ImageIcon("images/searchShut.jpg"), new ImageIcon("images/searchShut1.jpg")};
		GUIUtil.setJLabel(this.search_shut, image_shut[0], 179, 21, 23, 23);
		GUIUtil.setJLabel(this.search_label, image_search[0], 13, 20, 190, 25);
		GUIUtil.setTextField(this.search_text, 35, 20, 140, 25);
		
		this.search_text.setVisible(false);
		this.search_shut.setVisible(false);
		
		this.search_label.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				search_shut.setVisible(true);
				search_text.setVisible(true);
				search_text.requestFocus();
				search_label.setIcon(image_search[1]);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		this.search_shut.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				search_label.setIcon(image_search[0]);
				search_text.setText("");
				search_text.setVisible(false);
				search_shut.setVisible(false);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				search_shut.setIcon(image_shut[0]);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				search_shut.setIcon(image_shut[1]);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		Document document = this.search_text.getDocument();
		document.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				BookListPanel.instance.searchBookList(MainFrame.client.user.friendList, search_text.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				BookListPanel.instance.searchBookList(MainFrame.client.user.friendList, search_text.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.add(this.search_text, JLayeredPane.MODAL_LAYER);
		this.add(search_shut, JLayeredPane.MODAL_LAYER);
		this.add(search_label, JLayeredPane.DEFAULT_LAYER);
	}
}
