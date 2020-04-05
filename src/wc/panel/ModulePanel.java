package wc.panel;


import javax.swing.JPanel;

import util.ColorUtil;

public class ModulePanel extends JPanel {

	public static ModulePanel instance = new ModulePanel();

	private ModulePanel() {
		this.setVisible(true);
		this.setBounds(310, 0, 670, 59);
		this.setBackground(ColorUtil.GRAY_245);
	}
}
