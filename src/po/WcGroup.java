package po;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;

import util.Agreements;
import util.ColorUtil;
import util.GUIUtil;
import wc.frame.GroupChatFrame;

public class WcGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = Agreements.GET_GROUPS;
	private String groupId;
	private String hostId;
	private String groupName;
	public GroupChatFrame chat;
	public JLabel label;

	public WcGroup(String groupId, String hostId, String groupName) {
		this.groupId = groupId;
		this.hostId = hostId;
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setLabel() {
		this.label = new JLabel();
		this.label.setFont(GUIUtil.FANGSONG_14);
		label.setText(" ÈººÅ£º" + this.groupId + "  ÈºÃû£º" + this.groupName);
		label.setPreferredSize(new Dimension(245, 60));
		label.setBackground(ColorUtil.GRAY_233);
		label.setOpaque(true);
		this.chat = new GroupChatFrame(this);
		this.chat.members = new ArrayList<WcGroupMember>();
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				label.setBackground(ColorUtil.GRAY_233);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				label.setBackground(ColorUtil.GRAY_214);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				chat.setVisible(true);
				chat.setMembersList();
			}
		});
	}

}
