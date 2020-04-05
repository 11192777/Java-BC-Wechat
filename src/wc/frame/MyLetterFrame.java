package wc.frame;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import io.FileTemp;
import po.WcUser;
import util.Agreements;
import util.GUIUtil;
import wc.panel.ChosePanel;

public class MyLetterFrame extends JFrame implements ActionListener {

	static {
		GUIUtil.useSkin();
	}
	public static MyLetterFrame instance = null;

	private JLabel head = new JLabel();
	private JLabel name = new JLabel("网  名");
	private JTextField nameText = new JTextField();
	private JLabel sex = new JLabel("性  别");
	private JTextField sexText = new JTextField();
	private JLabel phone = new JLabel("电  话");
	private JTextField phoneText = new JTextField();
	private JLabel adress = new JLabel("地  址");
	private JTextField adressText = new JTextField();
	private JLabel sinature = new JLabel("签  名");
	private JTextField sinatureText = new JTextField();
	private JLabel id = new JLabel();

	private JButton sure = new JButton("保存");
	private JButton chose = new JButton("更换头像");
	private JButton passWd = new JButton("更改密码");
	private JButton buildGroup = new JButton("创建群聊");

	private FileDialog fd = null;
	private WcUser temp = null;
	private WcUser w = null;
	private boolean choseHead = false;

	public MyLetterFrame(WcUser w) {
		this.temp = w;
		this.w = new WcUser(w);
		this.setSize(500, 640);
		this.setLayout(null);
		this.setLocationRelativeTo(getOwner());
		this.setElements();
		this.setVisible(true);
	}

	public void setElements() {
		this.setFocusable(true);
		head.setIcon(w.head);
		head.setBounds(120, 60, 36, 36);
		name.setBounds(80, 130, 70, 25);
		name.setFont(new Font("黑体", Font.BOLD, 16));
		nameText.setBounds(160, 130, 250, 25);
		nameText.setText(w.getName());
		sex.setBounds(80, 200, 70, 25);
		sex.setFont(new Font("黑体", Font.BOLD, 16));
		sexText.setBounds(160, 200, 250, 25);
		sexText.setText(w.getSex());
		phone.setBounds(80, 270, 70, 25);
		phone.setFont(new Font("黑体", Font.BOLD, 16));
		phoneText.setBounds(160, 270, 250, 25);
		phoneText.setText(w.getPhone());
		adress.setBounds(80, 340, 70, 25);
		adress.setFont(new Font("黑体", Font.BOLD, 16));
		adressText.setBounds(160, 340, 250, 25);
		adressText.setText(w.getLocation());
		sinature.setBounds(80, 410, 70, 25);
		sinature.setFont(new Font("黑体", Font.BOLD, 16));
		sinatureText.setBounds(160, 410, 250, 25);
		sinatureText.setText(w.getSignature());
		id.setBounds(210, 65, 300, 25);
		id.setFont(new Font("黑体", Font.BOLD, 16));
		id.setText("ID:" + w.getId());
		sure.setBounds(350, 490, 80, 30);
		sure.setFocusable(false);
		chose.setBounds(250, 490, 80, 30);
		chose.setFocusable(false);
		passWd.setBounds(150, 490, 80, 30);
		passWd.setFocusable(false);
		buildGroup.setBounds(50, 490, 80, 30);
		buildGroup.setFocusable(false);
		sure.addActionListener(this);
		chose.addActionListener(this);
		passWd.addActionListener(this);
		buildGroup.addActionListener(this);
		this.add(buildGroup);
		this.add(head);
		this.add(sex);
		this.add(sexText);
		this.add(adress);
		this.add(adressText);
		this.add(id);
		this.add(phone);
		this.add(phoneText);
		this.add(sure);
		this.add(chose);
		this.add(sinature);
		this.add(passWd);
		this.add(sinatureText);
		this.add(name);
		this.add(nameText);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.chose) {
			fd = new FileDialog(new Frame(), "选择头像", FileDialog.LOAD);
			fd.setVisible(true);
			if (fd.getFile() != null) {
				if (!fd.getFile().endsWith(".jpg")) {
					JOptionPane.showMessageDialog(null, "请打开 .jpg 格式图片");
					choseHead = false;
					return;
				} else {
					w.head = new ImageIcon(fd.getDirectory() + fd.getFile());
					if (w.head.getIconHeight() > 50 || w.head.getIconWidth() > 50) {
						JOptionPane.showMessageDialog(null, "图片太大了！");
						return;
					}
					head.setIcon(w.head);
					choseHead = true;
				}
			} else {
				choseHead = false;
			}
		} else if (b == this.passWd) {
			String s = JOptionPane.showInputDialog("请输入原密码:");
			if (s == null) {
				return;
			}
			if (!s.equals(w.getPassWord())) {
				JOptionPane.showMessageDialog(null, "与原密码不一致.");
			} else {
				String pass = JOptionPane.showInputDialog("请输入新密码:");
				w.setPassWord(pass);
				JOptionPane.showMessageDialog(null, "密码已修改!");
			}
		} else if (b == this.buildGroup) {
			new BuildGroupFrame();
		} else {
			if (!sexText.getText().equals("男") && !sexText.getText().equals("女")) {
				JOptionPane.showMessageDialog(null, "额.....性别.......");
				return;
			}
			temp.head = w.head;
			temp.setPassWord(w.getPassWord());
			temp.setName(nameText.getText());
			temp.setSex(sexText.getText());
			temp.setPhone(phoneText.getText());
			temp.setLocation(adressText.getText());
			temp.setSignature(sinatureText.getText());
			ChosePanel.instance.head.setIcon(w.head);
			w.setName(nameText.getText());
			w.setSex(sexText.getText());
			w.setPhone(phoneText.getText());
			w.setLocation(adressText.getText());
			w.setSignature(sinatureText.getText());

			if (choseHead) {
				w.head = null;
			}
			MainFrame.client.writer.writeUser(w, Agreements.UPDATA_USER);
			if (choseHead) {
				try {
					FileInputStream fi = new FileInputStream(new File(fd.getDirectory() + fd.getFile()));
					int len;
					do {
						FileTemp ff = new FileTemp(temp.getId(), "null", "null");
						len = fi.read(ff.b);
						ff.length = len;
						MainFrame.client.writer.writeFile(ff, Agreements.FILE);
						System.out.println("写入头像");
					} while (len != -1);
					fi.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "保存成功!");
			this.setVisible(false);
		}
	}

}
