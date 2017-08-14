package core.service.security;

import java.util.HashMap;
import java.util.Map;

public class AuthContext {
	private static final String CURRENT_USER = "CURRENT_USER";
	private static final Map<String, Object> maps = new HashMap<String, Object>();
	
	public static String getCurrentUser() {
		String user = (String) maps.get(CURRENT_USER);
		if (user == null) {
			return "anonymous";
		}
		return user;
	}
	
	public static void setCurrentUser(String user) {
		maps.put(CURRENT_USER, user);
	}
}
