package core.common.utils;

public class CommonUtils {
	public static String concat(String... str) {
		StringBuilder builder = new StringBuilder();
		for (String s : str) {
			if (s!=null) {
				builder.append(s);
			}
		}
		
		return builder.toString();
	}
}
