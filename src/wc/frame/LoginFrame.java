package wc.frame;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import io.PassWord;
import util.Agreements;
import util.ColorUtil;
import util.GUIUtil;

public class LoginFrame extends JFrame implements MouseListener {

	public static LoginFrame instance = new LoginFrame();

	private JLayeredPane layeredPane = new JLayeredPane();
	private JTextField userName = new JTextField();
	private JPasswordField passWord = new JPasswordField();
	private Point location = null;
	private JButton close = new JButton();
	private ImageIcon[] close_image;
	private JButton setting = new JButton();
	private ImageIcon[] setting_image;
	private JButton login = new JButton();
	private ImageIcon[] login_image;
	private JLabel sign = new JLabel();

	private LoginFrame() {
		this.setMove();
		this.setSize(280, 400);
		this.setUndecorated(true);
		this.setLayout(null);
		this.setBackPicture();
		this.setTextFile();
		this.setButtons();
		this.setLayeredPane(layeredPane);
		this.setLocationRelativeTo(getOwner());
		this.addWindowListener(new closeWindow());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		com.sun.awt.AWTUtilities.setWindowShape(this,
				new RoundRectangle2D.Double(0.0D, 0.0D, this.getWidth(), this.getHeight(), 3.0D, 3.0D));
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

	private void setButtons() {
		close_image = new ImageIcon[] { new ImageIcon("images/close1.jpg"), new ImageIcon("images/close2.jpg"),
				new ImageIcon("images/close3.jpg") };
		setting_image = new ImageIcon[] { new ImageIcon("images/setting1.jpg"), new ImageIcon("images/setting2.jpg"),
				new ImageIcon("images/setting3.jpg") };
		login_image = new ImageIcon[] { new ImageIcon("images/login1.jpg"), new ImageIcon("images/login2.jpg") };

		GUIUtil.setButton(close, close_image[0], "关闭", 246, 0, 35, 25);
		GUIUtil.setButton(setting, setting_image[0], "设置", 212, 0, 35, 25);
		GUIUtil.setButton(login, login_image[0], null, 35, 260, 200, 39);
		GUIUtil.setLable(sign, "注册账户", ColorUtil.BLUE, 112, 350, 75, 20);

		close.addMouseListener(this);
		setting.addMouseListener(this);
		login.addMouseListener(this);
		sign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				SignFrame.instance = new SignFrame();
			}
		});

		this.layeredPane.add(close, JLayeredPane.MODAL_LAYER);
		this.layeredPane.add(setting, JLayeredPane.MODAL_LAYER);
		this.layeredPane.add(login, JLayeredPane.MODAL_LAYER);
		this.layeredPane.add(sign, JLayeredPane.MODAL_LAYER);
	}

	private void setTextFile() {
		this.userName.setBounds(95, 136, 150, 30);
		this.userName.setOpaque(false);
		this.userName.setBorder(null);
		this.userName.setFont(new Font("黑体", Font.BOLD, 16));
		this.layeredPane.add(this.userName, JLayeredPane.MODAL_LAYER);
		this.passWord.setBounds(95, 180, 150, 30);
		this.passWord.setOpaque(false);
		this.passWord.setBorder(null);
		this.layeredPane.add(this.passWord, JLayeredPane.MODAL_LAYER);
	}

	private void setBackPicture() {
		ImageIcon image = new ImageIcon("images/login.jpg");
		JLabel imageLable = new JLabel(image);
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(0, -15, 280, 420);
		imagePanel.add(imageLable);
		this.layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
	}

	public void verify() {
		MainFrame.client.writer.writePassWd(new PassWord(userName.getText(), new String(passWord.getPassword())));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			if (MainFrame.client!=null) {
				MainFrame.client.writer.writeAgreement(Agreements.CLOSE);
				MainFrame.client.reader.close();
			}
			System.exit(0);
		} 
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			this.close.setIcon(this.close_image[1]);
		} else if (b == this.setting) {
			this.setting.setIcon(this.setting_image[1]);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			this.close.setIcon(this.close_image[0]);
		} else {
			this.setting.setIcon(this.setting_image[0]);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			this.close.setIcon(this.close_image[2]);
		} else if (b == this.setting) {
			this.setting.setIcon(this.setting_image[2]);
		} else {
			login.setIcon(this.login_image[1]);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b == this.close) {
			this.close.setIcon(this.close_image[0]);
		} else if (b == this.setting) {
			this.setting.setIcon(this.setting_image[0]);
			SettingFrame.instance = new SettingFrame();
		} else {
			this.login.setIcon(this.login_image[0]);
			if (userName.getText().equals("") || this.userName.getText().length() < 5
					|| this.userName.getText().length() > 10) {
				JOptionPane.showMessageDialog(null, "请输入正确的用户名！");
				return;
			}
			this.verify();
			GUIUtil.sleepMoment(500);
			if (!MainFrame.client.haveOnline) {
				if (!MainFrame.client.haveLogin) {
					JOptionPane.showMessageDialog(null, "密码错误！");
					this.login.setIcon(this.login_image[0]);
				} else {
					LoginFrame.instance.setVisible(false);
					LoginFrame.instance = null;
					SettingFrame.instance = null;
					SignFrame.instance = null;
					MainFrame.instance = new MainFrame();
				}
			} else {
				JOptionPane.showMessageDialog(null, "用户已经在线！");
				MainFrame.client.haveOnline = false;
				MainFrame.client.haveLogin = false;
			}
		}
	}

	static class closeWindow extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			if (MainFrame.client != null) {
				MainFrame.client.writer.writeAgreement(Agreements.CLOSE);
			}
		}
	}
}
