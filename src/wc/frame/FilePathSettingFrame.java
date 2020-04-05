package wc.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import util.GUIUtil;

public class FilePathSettingFrame extends JFrame {
	public static String filePath = "D:\\";
	public static FilePathSettingFrame instance = null;
	static {
		GUIUtil.useSkin();
	}
	private JLabel path = new JLabel("文件保存路径:");
	private JTextField pathText = new JTextField();
	private JButton chose = new JButton("选择");
	private JButton save = new JButton("保存");

	public FilePathSettingFrame() {
		this.setSize(450, 150);
		this.setLayout(null);
		this.setLocationRelativeTo(getOwner());
		this.setElements();
		this.setVisible(true);
	}

	private void setElements() {
		path.setBounds(10, 20, 90, 25);
		pathText.setBounds(100, 20, 320, 30);
		pathText.setText(FilePathSettingFrame.filePath);
		chose.setBounds(140, 60, 70, 30);
		save.setBounds(230, 60, 70, 30);
		chose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					filePath = fileChooser.getSelectedFile().getAbsolutePath();
					pathText.setText(filePath);
				}
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FilePathSettingFrame.instance.setVisible(false);
			}
		});
		this.add(path);
		this.add(pathText);
		this.add(chose);
		this.add(save);
	}
	public static void main(String[] args) {
		new FilePathSettingFrame();
	}

}
