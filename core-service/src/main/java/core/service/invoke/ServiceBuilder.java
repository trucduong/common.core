package core.service.invoke;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import core.common.query.PagingInfo;
import core.common.query.RequestParamHelper;
import core.common.query.SortInfo;
import core.common.query.SortInfo.OrderType;
import core.service.utils.ServiceErrorCode;

public class ServiceBuilder {
	private Logger logger = LoggerFactory.getLogger(ServiceBuilder.class);
	
	private String url;
	private Map<String, Object> params;
	private Object data;
	private SortInfo sortInfo;
	private PagingInfo pagingInfo;

	private ServiceBuilder() {
		params = new HashMap<String, Object>();
		sortInfo = new SortInfo();
	}
	
	public static ServiceBuilder newService(String url) {
		ServiceBuilder builder = new ServiceBuilder();
		builder.url = url;
		return builder;
	}
	
	public static ServiceBuilder newService(String configKey, String serviceName, String action) {
		return ServiceBuilder.newService(buildRequestUrl(configKey, serviceName, action));
	}
	
	public ServiceBuilder addParam(String key, Object value) {
		params.put(key, value);
		return this;
	}

	public ServiceBuilder setPathParam(String key, Object value) {
		url = url.replaceAll("{"+key+"}", String.valueOf(value));
		return this;
	}
	
	public ServiceBuilder addParams(Map<String, Object> params) {
	    if (params!=null && !params.isEmpty()) {
	        this.params.putAll(params);
	    }
		return this;
	}
	
	public ServiceBuilder setData(Object data) {
		this.data = data;
		return this;
	}
	
	public ServiceBuilder addSortBy(String column) {
		sortInfo.addOrder(column, OrderType.ASC);
		return this;
	}
	
	public ServiceBuilder addSortBy(String column, String orderType) {
		sortInfo.addOrder(column, orderType);
		return this;
	}
	
	public ServiceBuilder setPaging(int firstIndex, int maxRecord) {
		pagingInfo = new PagingInfo();
		pagingInfo.setFirstIndex(firstIndex);
		pagingInfo.setMaxRecord(maxRecord);
		return this;
	}
	
	public ServiceResult get() {
		ServiceResult result;
		try {
			// TODO: check something before call service
			
			RequestParamHelper.setSortInfo(params, sortInfo);
			RequestParamHelper.setPagingInfo(params, pagingInfo);
			
			RestTemplate restTemplate = new RestTemplate();
			String url = buildUrlWithParams();
			logger.debug("GET: " + url);
			result = restTemplate.getForObject(url, ServiceResult.class);
		} catch (Exception e) {
			logger.error("GET error", e);
			result = ServiceResultHelper.error(ServiceErrorCode.CONNECTION_ERROR);
		}

		return result;
	}

	public ServiceResult post() {
		ServiceResult result;
		try {
			// TODO: check something before call service
			RestTemplate restTemplate = new RestTemplate();
			String url = buildUrlWithParams();
			logger.debug("POST: " + url);
			result = restTemplate.postForObject(url, data, ServiceResult.class);
		} catch (Exception e) {
			logger.error("POST error", e);
			result = ServiceResultHelper.error(ServiceErrorCode.CONNECTION_ERROR);
		}

		return result;
	}
	
	public String buildUrlWithParams() {
		StringBuilder urlBuilder = new StringBuilder(url);
		if (!params.isEmpty()) {
			urlBuilder.append("?");
			for (String param : params.keySet()) {
				urlBuilder.append(param).append("=").append(params.get(param)).append("&");
			}
			urlBuilder.deleteCharAt(urlBuilder.length()-1);
		}
		return urlBuilder.toString();
	}

	public static String buildRequestUrl(String configKey, String serviceName, String action) {
		return new StringBuilder(ServiceUrls.getServiceUrl(configKey))
				.append(serviceName).append(action).toString();
	}
	
	public static String buildRequestUrl(String configKey, String serviceName) {
        return new StringBuilder(ServiceUrls.getServiceUrl(configKey))
                .append(serviceName).toString();
    }
}
