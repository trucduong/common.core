package core.common.encode;

public class PasswordEncoder {

	public static String encode(String str) {
		return Md5Encoder.crypt(str);
	}
	
	public static boolean isValid(String rawPass, String encPass) {
		return encPass.equals(encode(rawPass));
	}
}
