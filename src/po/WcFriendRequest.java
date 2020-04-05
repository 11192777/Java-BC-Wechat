package po;

import java.io.Serializable;

import util.Agreements;

public class WcFriendRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = Agreements.BUILD_FRIEND;

	private String hostId;
	private String tailId;

	public WcFriendRequest(String hostId, String tailId) {
		this.hostId = hostId;
		this.tailId = tailId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
