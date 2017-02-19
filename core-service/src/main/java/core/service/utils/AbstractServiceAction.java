package core.service.utils;

import java.util.Map;

public abstract class AbstractServiceAction implements IServiceAction {
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
}
