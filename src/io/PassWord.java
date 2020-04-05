package io;

import java.io.Serializable;

import util.Agreements;

public class PassWord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = Agreements.ID_PASSWORD;
	private String id;
	private String passWord;
	
	public PassWord (String id, String passWord) {
		this.id = id;
		this.passWord = passWord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
