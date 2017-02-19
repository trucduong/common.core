package core.service.utils;

import java.util.Map;

public interface IServiceAction {
	String getServerName();
	String getServerUrl();

	String getUrl();
	Map<String, Object> getParams();
	Object getData();
}
