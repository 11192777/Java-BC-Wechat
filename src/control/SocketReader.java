package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import io.FileTemp;
import io.Message;
import io.WriteFileTemp;
import io.WriteFileToLocal;
import po.WcFriend;
import po.WcGroup;
import po.WcGroupMember;
import po.WcUser;
import server.Client;
import util.Agreements;
import util.ChineseCharToEn;
import util.ColorUtil;
import util.GUIUtil;
import wc.frame.FilePathSettingFrame;
import wc.frame.MainFrame;
import wc.frame.SignFrame;
import wc.panel.BookListPanel;
import wc.panel.ChosePanel;
import wc.panel.Messages;
import wc.panel.MyJLabel;
import wc.panel.NewFriendPanel;

public class SocketReader extends Thread {
	private boolean online = true;
	private InputStream is;
	private ObjectInputStream ois;
	private Client t;
	private ArrayList<String> getFileId = new ArrayList<>();

	public SocketReader(Socket socket, Client t) {
		this.t = t;
		try {
			is = socket.getInputStream();
			this.ois = new ObjectInputStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			while (this.online) {
				int sign = is.read();
				if (sign == -1) {
					break;
				}
				if (sign == Agreements.MESSAGE) {
					this.readMessage();
				} else if (sign == Agreements.FILE) {
//					file
				} else if (sign == Agreements.USER) {
					this.readObject();
				} else if (sign == Agreements.USER_PASS) {
					this.readUserPass();
				} else if (sign == Agreements.haveOnline) {
					this.readHaveOnLine();
				} else if (sign == Agreements.NOT_HAVETHISUSER) {
					JOptionPane.showMessageDialog(null, "不存在改用户！");
				} else if (sign == Agreements.YES_HAVETHISUSER) {
					this.readYseHaveUser();
				} else if (sign == Agreements.ADD_FRIENDS) {
					this.readAddFriends();
				} else if (sign == Agreements.UPDATA_FRIENDS) {
					this.readUpdataFriends();
				} else if (sign == Agreements.WINTOADD) {
					SignFrame.pass = true;
				} else if (sign == Agreements.LOSETOADD) {
					SignFrame.pass = false;
				} else if (sign == Agreements.FILE_MESSAGE) {
					this.readFileMessage();
				} else if (sign == Agreements.SEND_ERROR) {
					JOptionPane.showMessageDialog(null, "你已被该好友删除！");
				} else if (sign == Agreements.GET_GROUPS) {
					this.readGetGroups();
				} else if (sign == Agreements.GET_MEMBERS) {
					this.readGetMembers();
				} else if (sign == Agreements.SEND_GROUP_MESSAGE) {
					this.readGroupMessage();
				} else if (sign == Agreements.BUILF_GROUP_SECCESS) {
					JOptionPane.showMessageDialog(null, "创建成功！");
				} else if (sign == Agreements.BUILE_GROUP_LOSE) {
					JOptionPane.showMessageDialog(null, "创建失败，群ID重复！");
				} else if (sign == Agreements.RESTART_DELAYED) {
					int delayed = ois.read();
					WriteFileTemp.DELAYED = delayed;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	public void readGroupMessage() {
		try {
			Message m = (Message) ois.readObject();
			for (WcGroup p : BookListPanel.instance.groups) {
				if (p.getGroupId().equals(m.getReceiveId())) {
					p.chat.mess.insertString(m.getSendId() + "： " + m.getContent() + "\n");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readGetMembers() {
		try {
			WcGroupMember m = (WcGroupMember) ois.readObject();
			for (WcGroup p : BookListPanel.instance.groups) {
				if (p.getGroupId().equals(m.getGroupId())) {
					p.chat.members.add(m);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readGetGroups() {
		try {
			WcGroup p = (WcGroup) ois.readObject();
			for (WcGroup g : BookListPanel.instance.groups) {
				if (g.getGroupId().equals(p.getGroupId())) {
					return;
				}
			}
			p.setLabel();
			BookListPanel.instance.groups.add(p);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFileMessage() {
		try {
			FileTemp ft = (FileTemp) ois.readObject();
			if (ft.length == -1) {
				if (getFileId.contains(ft.sendId + ft.fileName)) {
					getFileId.remove(ft.sendId + ft.fileName);
				}
				if (getFileId.contains(ft.fileName)) {
					getFileId.remove(ft.fileName);
				}
				return;
			}

			if (!getFileId.contains(ft.sendId + ft.fileName) && !getFileId.contains(ft.fileName)) {
				int n = JOptionPane.showConfirmDialog(null, "来自微信号:[" + ft.sendId + "]的" + ft.fileName + "文件", "确认接收框",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					getFileId.add(ft.sendId + ft.fileName);
					File file = new File(FilePathSettingFrame.filePath + "/" + ft.fileName);
					if (file.exists()) {
						file.delete();
					}
				} else {
					getFileId.add(ft.fileName);
				}
			}

			if (getFileId.contains(ft.sendId + ft.fileName)) {
				if (ft.length != -1) {
					WriteFileToLocal.write(ft.fileName, FilePathSettingFrame.filePath, ft);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readUpdataFriends() {
		WcUser w = null;
		try {
			w = (WcUser) ois.readObject();
			w.label = new MyJLabel();
			w.label.addMouseListener(BookListPanel.instance);
			w.mess = new Messages();
			WcFriend f = new WcFriend(w, 0, null);
			GUIUtil.setMyJlabel(w.label, f, w.getName(), w.head);
			f.index = (int) ChineseCharToEn.getFirstLetter(w.getName()).toCharArray()[0];
			MainFrame.client.user.friendList.add(f);
			Collections.sort(MainFrame.client.user.friendList);
			BookListPanel.instance.setBookLisetPanel(MainFrame.client.user.friendList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readAddFriends() {
		try {
			WcUser w = (WcUser) ois.readObject();
			NewFriendPanel.instance.addUserButton(w);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readYseHaveUser() {
		try {
			WcUser w = (WcUser) ois.readObject();
			NewFriendPanel.instance.setNewFriend(w);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readHaveOnLine() {
		MainFrame.client.haveOnline = true;
		MainFrame.client.haveLogin = true;
	}

	public void readUserPass() {
		MainFrame.client.haveOnline = false;
		MainFrame.client.haveLogin = true;
	}

	public void readObject() {
		WcUser w = null;
		try {
			w = (WcUser) ois.readObject();
			t.user = w;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readMessage() {
		Message m = null;
		try {
			m = (Message) ois.readObject();
			for (WcFriend w : MainFrame.client.user.friendList) {
				if (w.tail.getId().equals(m.getSendId())) {
					w.tail.mess.insert(m.getContent(), w.tail.head, false);
					w.tail.label.setBackground(ColorUtil.red);
					if (w.dialogue == 0) {
						w.dialogue = BookListPanel.instance.dialogue.size() + 1;
						BookListPanel.instance.dialogue.add(w);
						BookListPanel.instance.setDialoguePanel();
						ChosePanel.instance.message.doClick();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.is.close();
			this.ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
