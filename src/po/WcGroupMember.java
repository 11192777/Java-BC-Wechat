package po;

import java.io.Serializable;

import util.Agreements;

public class WcGroupMember implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = Agreements.GET_MEMBERS		;
	private String groupId;
	private String tailId;

	public WcGroupMember(String groupId, String tailId) {
		this.groupId = groupId;
		this.tailId = tailId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTailId() {
		return tailId;
	}

	public void setTailId(String tailId) {
		this.tailId = tailId;
	}
}
