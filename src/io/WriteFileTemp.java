package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import util.Agreements;
import util.GUIUtil;
import wc.frame.MainFrame;

public class WriteFileTemp implements Runnable{
	public static int DELAYED = 5;
	private String sendId;
	private String getId;
	private String fileName;

	private FileInputStream fis;

	public WriteFileTemp(String sendId, String getId, String fileName, String path) {
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sendId = sendId;
		this.getId = getId;
		this.fileName = fileName;
	}

	public synchronized void run() {
		int len = -1;
		do {
			try {
				FileTemp ft = new FileTemp(sendId, getId, fileName);
				len = fis.read(ft.b);
				ft.length = len;
				MainFrame.client.writer.writeFile(ft,Agreements.FILE_MESSAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GUIUtil.sleepMoment(DELAYED);
		} while (len != -1);
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
