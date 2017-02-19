package core.service.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import core.common.exception.CommonException;
import core.service.utils.ServiceErrorCode;

public class AuthorInterceptor extends HandlerInterceptorAdapter {
	private AuthProvider provider;
	
	public AuthorInterceptor(AuthProvider provider) {
		super();
		this.provider = provider;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String servletPath = request.getServletPath();
		
//		if (provider.isByPassAuthentication(servletPath)) {
//			return true;
//		}
//		
//		String authKey = request.getHeader(AuthorizationUser.T_AUTH_TOKEN);
//		if (authKey == null) {
//			throw new CommonException(ServiceErrorCode.ACCESS_DENIED, "Please login!");
//		}
//		
//		AuthorizationUser user = AuthorizationUser.fromString(authKey);
//		if (!user.isAuthenticated()) {
//			throw new CommonException(ServiceErrorCode.ACCESS_DENIED, "Authorization token error!");
//		}
//		
//		if (provider.isByPassAuthorization(servletPath)) {
//			return true;
//		}
//		
//		if(!user.hasPermission(servletPath)) {
//			throw new CommonException(ServiceErrorCode.ACCESS_DENIED, "User do not have permission to access!");
//		}
		
		return true;
	}
}
