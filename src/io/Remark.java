package io;

import java.io.Serializable;

import util.Agreements;

public class Remark implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = Agreements.UPDATA_REMARK;
	private String hostId;
	private String tailId;
	private int dialogue;
	private String remark;

	public Remark(String hostId, String tailId, int dialogue, String remark) {
		this.hostId = hostId;
		this.dialogue = dialogue;
		this.tailId = tailId;
		this.remark = remark;
	}

	public int getDialogue() {
		return dialogue;
	}

	public void setDialogue(int dialogue) {
		this.dialogue = dialogue;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getTailId() {
		return tailId;
	}

	public void setTailId(String tailId) {
		this.tailId = tailId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
