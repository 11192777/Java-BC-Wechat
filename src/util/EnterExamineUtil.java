package util;

public class EnterExamineUtil {

	public static boolean passWordExamine(String passWd) {
		if (passWd.length() < 8 || passWd.length() > 16) {
			return false;
		}
		char[] arr = passWd.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < '0' || arr[i] > 'z') {
				return false;
			}
		}
		return true;
	}

	public static boolean idExamine(String id) {
		if (id.length() < 5 || id.length() > 10) {
			return false;
		}
		char[] arr = id.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < '0' || arr[i] > '9') {
				return false;
			}
		}
		return true;

	}
}
