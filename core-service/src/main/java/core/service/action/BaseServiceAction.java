package core.service.action;

import org.springframework.core.env.Environment;

public abstract class BaseServiceAction {
	private static final String SERVICE_NAME = ".server.name";
	private static final String SERVICE_URL = "..server.url";
	
	private final String baseUrl;
	
	public BaseServiceAction(Environment env) {
		baseUrl = new StringBuilder(env.getProperty(getServiceName() + SERVICE_URL)).append(env.getProperty(getServiceName() + SERVICE_NAME)).toString();
	}
	
	protected String createRequestUrl(String action) {
		return baseUrl + action;
	}
	
	protected abstract String getServiceName();
}
