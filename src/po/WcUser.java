package po;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import util.Agreements;
import wc.panel.Messages;
import wc.panel.MyJLabel;

public class WcUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = Agreements.USER;
	private String id;
	private String passWord;
	private String name;
	private String sex;
	private String phone;
	private String location;
	private String signature;
	public ArrayList<WcFriend> friendList;
	public ImageIcon head;
	public Messages mess;
	public MyJLabel label;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public WcUser(WcUser w) {
		this.id = w.id;
		this.passWord = w.passWord;
		this.name = w.name;
		this.sex = w.sex;
		this.phone = w.phone;
		this.location = w.location;
		this.signature = w.signature;
		this.head = w.head;
	}

	public WcUser(String id, String passWord, String name, String sex, String phone, String location,
			String signature) {
		this.id = id;
		this.passWord = passWord;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.location = location;
		this.signature = signature;
		this.friendList = new ArrayList<>();
	}

	public WcUser() {
		this.id = null;
		this.passWord = null;
		this.name = null;
		this.sex = null;
		this.phone = null;
		this.location = null;
		this.signature = null;
		this.friendList = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void addOne() {
		for (WcFriend w : this.friendList) {
			if (w.dialogue != 0) {
				w.dialogue++;
			}
		}
	}

	public void setHead() {
		if (this.id != null) {
			this.head = new ImageIcon("headImages/" + this.id + ".jpg");
		}
	}
}
