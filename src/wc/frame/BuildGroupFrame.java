package wc.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Native;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import io.Message;
import util.Agreements;
import util.EnterExamineUtil;
import util.GUIUtil;

public class BuildGroupFrame extends JFrame implements ActionListener {

	static {
		GUIUtil.useSkin();
	}
	private JLabel groupId = new JLabel("群 I D：");
	private JTextField idText = new JTextField();
	private JLabel groupName = new JLabel("群名称：");
	private JTextField nameText = new JTextField();
	private JButton sign = new JButton("注册");

	public BuildGroupFrame() {
		this.setSize(500, 500);
		this.setLayout(null);
		this.setLocationRelativeTo(getOwner());
		this.setElements();
		this.setVisible(true);
	}

	public void setElements() {
		groupId.setBounds(80, 130, 70, 25);
		groupId.setFont(new Font("黑体", Font.BOLD, 16));
		idText.setBounds(160, 130, 250, 25);
		groupName.setBounds(80, 200, 70, 25);
		groupName.setFont(new Font("黑体", Font.BOLD, 16));
		nameText.setBounds(160, 200, 250, 25);
		sign.setBounds(210, 340, 80, 30);
		sign.addActionListener(this);
		this.add(groupId);
		this.add(idText);
		this.add(groupName);
		this.add(nameText);
		this.add(sign);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.sign) {
			if (!EnterExamineUtil.idExamine(idText.getText())) {
				JOptionPane.showMessageDialog(null, "输入有效的ID（5 - 10）位。");
				return;
			}
			String name = nameText.getText();
			if (name.length() > 8) {
				name = name.substring(0, 8);
			}
			MainFrame.client.writer.writeMessage(new Message(MainFrame.client.user.getId(), idText.getText(), name),
					Agreements.BUILD_GROUP);
		}
	}

}
