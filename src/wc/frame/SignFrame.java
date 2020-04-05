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
	private JLabel user = new JLabel("�û���");
	private JTextField userText = new JTextField(10);
	private JLabel name = new JLabel("��  ��");
	private JTextField nameText = new JTextField();
	private String[] sexList = { "��", "Ů" };
	private JComboBox<String> sexText = new JComboBox<String>(sexList);
	private JLabel sex = new JLabel("��  ��");
//	private JTextField sexText = new JTextField();
	private JLabel phone = new JLabel("��  ��");
	private JTextField phoneText = new JTextField();
	private JLabel adress = new JLabel("��  ַ");
//	private JTextField adressText = new JTextField();
	private JComboBox<String> provinceText;
	private JComboBox<String> cityText;
	private JLabel sinature = new JLabel("ǩ  ��");
	private JTextField sinatureText = new JTextField();
	private JLabel passWd = new JLabel("��  ��");
	private JTextField passWdText = new JTextField();
	private JButton sure = new JButton("ע��");
	private JButton chose = new JButton("ѡ��ͷ��");
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
		String[] province = { "- - - -", "������", "����", "�Ϻ�", "���ɹ�" };
		String[][] city = { { "- - - -" }, { "- - - -", "��������", "�峣��" }, { "- - - -", "������" }, { "- - - -", "�Ϻ���" },
				{ "- - - -", "��³ľ��", "���ͺ���" } };
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
		user.setFont(new Font("����", Font.BOLD, 16));
		userText.setBounds(160, 60, 250, 25);
		name.setBounds(80, 130, 70, 25);
		name.setFont(new Font("����", Font.BOLD, 16));
		nameText.setBounds(160, 130, 250, 25);
		sex.setBounds(80, 200, 70, 25);
		sex.setFont(new Font("����", Font.BOLD, 16));
		sexText.setBounds(160, 200, 250, 25);
		phone.setBounds(80, 270, 70, 25);
		phone.setFont(new Font("����", Font.BOLD, 16));
		phoneText.setBounds(160, 270, 250, 25);
		adress.setBounds(80, 340, 70, 25);
		adress.setFont(new Font("����", Font.BOLD, 16));
//		adressText.setBounds(160, 340, 250, 25);
		provinceText.setBounds(160, 340, 120, 25);
		cityText.setBounds(290, 340, 120, 25);
		sinature.setBounds(80, 410, 70, 25);
		sinature.setFont(new Font("����", Font.BOLD, 16));
		sinatureText.setBounds(160, 410, 250, 25);
		passWd.setBounds(80, 480, 70, 25);
		passWd.setFont(new Font("����", Font.BOLD, 16));
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
				fd = new FileDialog(new Frame(), "ѡ��ͼƬ", FileDialog.LOAD);
				fd.setVisible(true);
				if (fd.getFile() != null) {
					if (!fd.getFile().endsWith(".jpg")) {
						JOptionPane.showMessageDialog(null, "��� .jpg ��ʽͼƬ");
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
					JOptionPane.showMessageDialog(null, "�û���Ϊ��5-10��λ��Ч����");
					return;
				}
				w.setSex(sexList[sexText.getSelectedIndex()]);
				w.setName(nameText.getText());
				w.setPhone(phoneText.getText());
				w.setLocation(provinceName + cityName);
				w.setSignature(sinatureText.getText());
				w.setPassWord(passWdText.getText());
				if (!EnterExamineUtil.passWordExamine(passWdText.getText())) {
					JOptionPane.showMessageDialog(null, "����Ϊ��8-16)������ĸ���");
					return;
				}
				MainFrame.client.writer.writeUser(w, Agreements.ADD_USER);
				GUIUtil.sleepMoment(500);
				if (pass) {
					JOptionPane.showMessageDialog(null, "ע��ɹ���");
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
					JOptionPane.showMessageDialog(null, "���û��Ѿ����ڣ�");
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
