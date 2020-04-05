package io;

import java.io.Serializable;

import util.Agreements;

public class FileTemp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = Agreements.FILE;
	public String sendId;
	public String getId;
	public String fileName;
	public int length;
	public byte[] b = new byte[4096];

	public FileTemp(String sendId, String getId, String fileName) {
		this.sendId = sendId;
		this.getId = getId;
		this.fileName = fileName;
	}

}
