package core.service.security;

import java.util.Date;
import java.util.List;

import core.common.encode.Base64Encoder;
import core.common.format.json.JsonFormatter;

public class AuthorizationUser {
	public static String T_AUTH_TOKEN = "T_AUTH_TOKEN";
	
	private String loginName;
	private Date loggedTime;
	private List<String> permissions;
	
	@Override
	public String toString() {
		String json = JsonFormatter.toJson(this);
		return Base64Encoder.encode(json);
	}
	
	public static AuthorizationUser fromString(String authToken) {
		AuthorizationUser user = null;
		try {
			String json = Base64Encoder.decode(authToken);
			user = JsonFormatter.fromJson(json, AuthorizationUser.class);
		} catch (Exception e) {
			user = new AuthorizationUser();
		}
		return user;
	}
	
	public boolean hasPermission(String permission) {
		if (getPermissions() != null) {
			return getPermissions().contains(permission);
		}
		return false;
	}
	
	public boolean isAuthenticated() {
		return this.getLoginName() != null;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getLoggedTime() {
		return loggedTime;
	}

	public void setLoggedTime(Date loggedTime) {
		this.loggedTime = loggedTime;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}
