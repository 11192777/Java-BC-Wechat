package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFileToLocal {
	private static FileOutputStream fos;

	public static synchronized void write(String name, String path, FileTemp ft) {
		try {
			File file = new File(path + "/" + name);
			if (!file.exists()) {
				file.createNewFile();
				fos = new FileOutputStream(file);
			} else {
				fos = new FileOutputStream(file, true);
			}
			fos.write(ft.b, 0, ft.length);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
