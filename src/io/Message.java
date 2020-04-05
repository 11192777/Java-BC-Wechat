package io;

import java.io.Serializable;

import util.Agreements;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = Agreements.MESSAGE;
	private String sendId;
	private String receiveId;
	private String content;

	public Message(String sendId, String receiveId, String content) {
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.content = content;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
