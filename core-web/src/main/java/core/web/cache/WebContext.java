package core.web.cache;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebContext {

    private static final Map<String, Object> maps = new HashMap<>();
    
	public static HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession();
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest();
	}

	public static String getServletPath() {
		HttpServletRequest request = getRequest();
		return request.getServletPath();
	}
	
	public static String getCurrentUser() {
        String user = (String) maps.get("CURRENT_USER");
        if (user == null) {
            return "anonymous";
        }
        return user;
    }
}
