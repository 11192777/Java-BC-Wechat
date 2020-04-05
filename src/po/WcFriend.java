package po;

import java.io.Serializable;

public class WcFriend implements Serializable, Comparable<WcFriend> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 20175930L;
	public WcUser tail;
	public int dialogue;
	public int index;
	public String remark;

	public WcFriend(WcUser tail, int dialogue, String remark) {
		this.tail = tail;
		this.dialogue = dialogue;
		this.remark = remark;
	}

	public WcFriend(WcUser tail, int dialogue, String remark,int index) {
		this.tail = tail;
		this.dialogue = dialogue;
		this.remark = remark;
		this.index = index;
	}

	public void setTail(WcUser tail) {
		this.tail = tail;
	}

	public WcUser getTail() {
		return this.tail;
	}

	public int getDialogue() {
		return dialogue;
	}

	public void setDialogue(int dialogue) {
		this.dialogue = dialogue;
	}

	public boolean equals(WcFriend w) {
		return this.tail.getId().equals(w.tail.getId());
	}

	@Override
	public int compareTo(WcFriend o) {
		// TODO Auto-generated method stub
		return this.index >= o.index ? 1 : -1;
	}
}
