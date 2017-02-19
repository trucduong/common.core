package core.service.security;

import java.util.List;

public class AuthProvider {
	private List<String> bypass;
	public AuthProvider(List<String> bypass) {
		this.bypass = bypass;
	}
	
	public boolean isByPassAuthentication(String url) {
		return this.bypass.contains(url);
	}
	
	public boolean isByPassAuthorization(String url) {
		if (url.startsWith("/ref-service")) {
			return true;
		}
		return false;
	}
}
