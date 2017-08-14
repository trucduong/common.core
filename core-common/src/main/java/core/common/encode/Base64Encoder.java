package core.common.encode;

import org.apache.commons.codec.binary.Base64;

public class Base64Encoder {
	public static String encode(String str) {
		byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
		return new String(bytesEncoded);
	}

	public static String decode(String str) {
		byte[] valueDecoded = Base64.decodeBase64(str.getBytes());
		return new String(valueDecoded);
	}
}
