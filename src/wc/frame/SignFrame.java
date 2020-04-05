package wc.frame;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import io.FileTemp;
import po.WcUser;
import util.Agreements;
import util.EnterExamineUtil;
import util.GUIUtil;

public class SignFrame extends JFrame {
	static {
		GUIUtil.useSkin();
	}
	public static SignFrame instance = null;
	public static boolean pass = false;
	private boolean pictrue = false;
	private WcUser w = new WcUser();
	private JLabel user = new JLabel("用户名");
	private JTextField userText = new JTextField(10);
	private JLabel name = new JLabel("网  名");
	private JTextField nameText = new JTextField();
	private String[] sexList = { "男", "女" };
	private JComboBox<String> sexText = new JComboBox<String>(sexList);
	private JLabel sex = new JLabel("性  别");
//	private JTextField sexText = new JTextField();
	private JLabel phone = new JLabel("电  话");
	private JTextField phoneText = new JTextField();
	private JLabel adress = new JLabel("地  址");
//	private JTextField adressText = new JTextField();
	private JComboBox<String> provinceText;
	private JComboBox<String> cityText;
	private JLabel sinature = new JLabel("签  名");
	private JTextField sinatureText = new JTextField();
	private JLabel passWd = new JLabel("密  码");
	private JTextField passWdText = new JTextField();
	private JButton sure = new JButton("注册");
	private JButton chose = new JButton("选择头像");
	private FileDialog fd = null;
	private String provinceName = "";
	private String cityName = "";

	public SignFrame() {
		this.setSize(500, 630);
		this.setLayout(null);
		this.setLocationRelativeTo(getOwner());
		this.setAdress();
		this.setElements();
		this.setVisible(true);
	}

	public void setAdress() {
		String[] province = { "- - - -", "黑龙江", "北京", "上海", "内蒙古" };
		String[][] city = { { "- - - -" }, { "- - - -", "哈尔滨市", "五常市" }, { "- - - -", "北京市" }, { "- - - -", "上海市" },
				{ "- - - -", "乌鲁木齐", "呼和浩特" } };
		provinceText = new JComboBox<String>(province);
		cityText = new JComboBox<String>(city[0]);
		provinceText.setFocusable(false);
		cityText.setFocusable(false);
		provinceText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (provinceText.getSelectedIndex() == 0) {
					provinceName = "";
					cityName = "";
					cityText.removeAllItems();
					cityText.addItem(city[0][0]);
				} else {
					provinceName = province[provinceText.getSelectedIndex()];
					cityText.removeAllItems();
					for (int i = 0; i < city[provinceText.getSelectedIndex()].length; i++) {
						cityText.addItem(city[provinceText.getSelectedIndex()][i]);
					}
				}
			}
		});
		cityText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cityText.getSelectedIndex() == -1 || cityText.getSelectedIndex() == 0) {
					cityName = "";
				} else {
					cityName = city[provinceText.getSelectedIndex()][cityText.getSelectedIndex()];
				}
				System.out.println(provinceName + cityName);
			}
		});
		this.add(provinceText);
		this.add(cityText);

	}

	public void setElements() {
		user.setBounds(80, 60, 70, 25);
		user.setFont(new Font("黑体", Font.BOLD, 16));
		userText.setBounds(160, 60, 250, 25);
		name.setBounds(80, 130, 70, 25);
		name.setFont(new Font("黑体", Font.BOLD, 16));
		nameText.setBounds(160, 130, 250, 25);
		sex.setBounds(80, 200, 70, 25);
		sex.setFont(new Font("黑体", Font.BOLD, 16));
		sexText.setBounds(160, 200, 250, 25);
		phone.setBounds(80, 270, 70, 25);
		phone.setFont(new Font("黑体", Font.BOLD, 16));
		phoneText.setBounds(160, 270, 250, 25);
		adress.setBounds(80, 340, 70, 25);
		adress.setFont(new Font("黑体", Font.BOLD, 16));
//		adressText.setBounds(160, 340, 250, 25);
		provinceText.setBounds(160, 340, 120, 25);
		cityText.setBounds(290, 340, 120, 25);
		sinature.setBounds(80, 410, 70, 25);
		sinature.setFont(new Font("黑体", Font.BOLD, 16));
		sinatureText.setBounds(160, 410, 250, 25);
		passWd.setBounds(80, 480, 70, 25);
		passWd.setFont(new Font("黑体", Font.BOLD, 16));
		passWdText.setBounds(160, 480, 250, 25);
		sure.setBounds(290, 540, 80, 30);
		sure.setFocusable(false);
		chose.setBounds(130, 540, 80, 30);
		chose.setFocusable(false);
		sexText.setFocusable(false);
		this.add(user);
		this.add(userText);
		this.add(sex);
		this.add(sexText);
		this.add(adress);
		this.add(passWd);
		this.add(passWdText);
		this.add(phone);
		this.add(phoneText);
		this.add(sure);
		this.add(chose);
		this.add(sinature);
		this.add(sinatureText);
		this.add(name);
		this.add(nameText);
		chose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fd = new FileDialog(new Frame(), "选择图片", FileDialog.LOAD);
				fd.setVisible(true);
				if (fd.getFile() != null) {
					if (!fd.getFile().endsWith(".jpg")) {
						JOptionPane.showMessageDialog(null, "请打开 .jpg 格式图片");
						return;
					}
					pictrue = true;
				}
			}
		});
		sure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				w.setId(userText.getText());
				if (!idExamine(userText.getText())) {
					JOptionPane.showMessageDialog(null, "用户名为（5-10）位有效数字");
					return;
				}
				w.setSex(sexList[sexText.getSelectedIndex()]);
				w.setName(nameText.getText());
				w.setPhone(phoneText.getText());
				w.setLocation(provinceName + cityName);
				w.setSignature(sinatureText.getText());
				w.setPassWord(passWdText.getText());
				if (!EnterExamineUtil.passWordExamine(passWdText.getText())) {
					JOptionPane.showMessageDialog(null, "密码为（8-16)数字字母组合");
					return;
				}
				MainFrame.client.writer.writeUser(w, Agreements.ADD_USER);
				GUIUtil.sleepMoment(500);
				if (pass) {
					JOptionPane.showMessageDialog(null, "注册成功！");
					w = new WcUser();
					int len = -1;
					if (!pictrue) {
						try {
							FileInputStream fi = new FileInputStream(new File("images/defaultHead.jpg"));
							do {
								FileTemp ff = new FileTemp(userText.getText(), "null", "null");
								len = fi.read(ff.b);
								ff.length = len;
								MainFrame.client.writer.writeFile(ff, Agreements.FILE);
							} while (len != -1);
							fi.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							FileInputStream fi = new FileInputStream(new File(fd.getDirectory() + fd.getFile()));
							do {
								FileTemp ff = new FileTemp(userText.getText(), "null", "null");
								len = fi.read(ff.b);
								ff.length = len;
								MainFrame.client.writer.writeFile(ff, Agreements.FILE);
							} while (len != -1);
							fi.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "该用户已经存在！");
					w = new WcUser();
				}
			}
		});
	}

	public boolean idExamine(String id) {
		if (id.length() < 5 || id.length() > 10) {
			return false;
		}
		char[] arr = id.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if ((int) arr[i] < 48 || (int) arr[i] > 58) {
				return false;
			}
		}
		return true;
	}

}
