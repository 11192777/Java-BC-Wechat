package control;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import io.FileTemp;
import io.Message;
import io.PassWord;
import io.Remark;
import po.WcFriendRequest;
import po.WcGroupMember;
import po.WcUser;
import util.Agreements;

public class SocketWriter {
	private Socket socket;
	private OutputStream os;
	private ObjectOutputStream oos;

	public SocketWriter(Socket socket) {
		this.socket = socket;
		try {
			this.os = socket.getOutputStream();
			this.oos = new ObjectOutputStream(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void writeMessage(Message m, int agreement) {
		this.writeAgreement(agreement);
		try {
			oos.writeObject(m);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized void writeGroupMember(WcGroupMember m, int agreement) {
		this.writeAgreement(agreement);
		try {
			oos.writeObject(m);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void writePassWd(PassWord p) {
		this.writeAgreement(Agreements.ID_PASSWORD);
		try {
			oos.writeObject(p);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void writeUser(WcUser u, int agrement) {
		this.writeAgreement(agrement);
		try {
			oos.writeObject(u);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void writeAgreement(int agr) {
		try {
			os.write(agr);
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "¶Ï¿ªÁËÍøÂç£¡");
		}
	}

	public synchronized void writeRequest(WcFriendRequest r, int agreement) {
		this.writeAgreement(agreement);
		try {
			oos.writeObject(r);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized void writeString(int agrement, String s) {
		this.writeAgreement(agrement);
		try {
			oos.writeObject(new String(s));
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void writeRemark(Remark r) {
		this.writeAgreement(Agreements.UPDATA_REMARK);
		try {
			oos.writeObject(r);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void writeFile(FileTemp t, int agreemrnt) {
		this.writeAgreement(agreemrnt);
		try {
			oos.writeObject(t);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
