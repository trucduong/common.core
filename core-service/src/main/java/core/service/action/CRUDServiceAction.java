package core.service.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

import core.service.invoke.ServiceHelper;
import core.service.invoke.ServiceResult;

public abstract class CRUDServiceAction extends BaseServiceAction {

	public CRUDServiceAction(Environment env) {
		super(env);
	}
	
	public ServiceResult read(String id) {
		String url = createRequestUrl(CRUDServiceActionConst.READ);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CRUDServiceActionConst.PARAM_ID, id);
		return ServiceHelper.get(url, params);
	}
	
	public ServiceResult readAll(Environment env) {
		String url = createRequestUrl(CRUDServiceActionConst.READ_ALL);
		
		Map<String, Object> params = new HashMap<String, Object>();
		return ServiceHelper.get(url, params);
	}
	
	public ServiceResult readBy(Environment env, String name, String value) {
		String url = createRequestUrl(CRUDServiceActionConst.READ_BY);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CRUDServiceActionConst.PARAM_NAME, name);
		params.put(CRUDServiceActionConst.PARAM_VALUE, value);
		return ServiceHelper.get(url, params);
	}
	
	public ServiceResult delete(Environment env, String id) {
		String url = createRequestUrl(CRUDServiceActionConst.READ);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CRUDServiceActionConst.PARAM_ID, id);
		return ServiceHelper.get(url, params);
	}
	
	public ServiceResult create(Environment env, Object value) {
		String url = createRequestUrl(CRUDServiceActionConst.READ);
		
		Map<String, Object> params = new HashMap<String, Object>();
		return ServiceHelper.post(url, params, value);
	}
	
	public ServiceResult update(Environment env, String id, Object value) {
		String url = createRequestUrl(CRUDServiceActionConst.READ);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CRUDServiceActionConst.PARAM_ID, id);
		return ServiceHelper.post(url, params, value);
	}
}
