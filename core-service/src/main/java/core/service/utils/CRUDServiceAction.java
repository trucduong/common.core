package core.service.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

public class CRUDServiceAction implements IServiceAction {
	public static final String PARAM_ID = "id";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_VALUE = "value";
	public static final String PARAM_VALUES = "values";
	
	public static final String READ = "/read/{id}";
	public static final String READ_ALL = "/read-all";
	public static final String READ_BY = "/read-by/{name}/{value}";
	public static final String READ_ALL_BY = "/read-all-by/{name}/{values}";
	
	public static final String CREATE = "/create";
	public static final String UPDATE = "/update/{id}";
	public static final String DELETE = "/delete/{id}";
	
	
	protected String url;
	protected Map<String, Object> params;
	protected Object data;

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public Map<String, Object> getParams() {
		return params;
	}

	@Override
	public Object getData() {
		return data;
	}
	
	// Child class overwrite
	@Override
	public String getServerName() {
		return null;
	}

	// Child class overwrite
	@Override
	public String getServerUrl() {
		return null;
	}
	
	public IServiceAction read(Environment env, String id) {
		this.url = new StringBuilder(env.getProperty(getServerUrl()))
				.append(env.getProperty(getServerName()))
				.append(READ).toString();
		
		this.params = new HashMap<String, Object>();
		this.params.put(PARAM_ID, id);
		return this;
	}
	
	public IServiceAction readAll(Environment env) {
		this.url = new StringBuilder(env.getProperty(getServerUrl()))
				.append(env.getProperty(getServerName()))
				.append(READ_ALL).toString();
		
		this.params = new HashMap<String, Object>();
		return this;
	}
	
	public IServiceAction readBy(Environment env, String name, String value) {
		this.url = new StringBuilder(env.getProperty(getServerUrl()))
				.append(env.getProperty(getServerName()))
				.append(READ_BY).toString();
		
		this.params = new HashMap<String, Object>();
		this.params.put(PARAM_NAME, name);
		this.params.put(PARAM_VALUE, value);
		return this;
	}
	
	public IServiceAction delete(Environment env, String id) {
		this.url = new StringBuilder(env.getProperty(getServerUrl()))
				.append(env.getProperty(getServerName()))
				.append(DELETE).toString();
		
		this.params = new HashMap<String, Object>();
		this.params.put(PARAM_ID, id);
		return this;
	}
	
	public IServiceAction create(Environment env, Object value) {
		this.url = new StringBuilder(env.getProperty(getServerUrl()))
				.append(env.getProperty(getServerName()))
				.append(CREATE).toString();
		
		this.params = new HashMap<String, Object>();
		this.data = value;
		return this;
	}
	
	public IServiceAction update(Environment env, String id, Object value) {
		this.url = new StringBuilder(env.getProperty(getServerUrl()))
				.append(env.getProperty(getServerName()))
				.append(CREATE).toString();
		
		this.params = new HashMap<String, Object>();
		this.params.put(PARAM_ID, id);
		this.data = value;
		return this;
	}
}
