package core.service.invoke;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import core.service.utils.ServiceErrorCode;

public class ServiceHelper {

	private static RestTemplate restTemplate = new RestTemplate();

	public static ServiceResult get(String url, Map<String, Object> params) {
		ServiceResult result;
		try {

			// TODO: check something before call service
			result = restTemplate.getForObject(url, ServiceResult.class, params);
		} catch (Exception e) {
			result = ServiceHelper.error(ServiceErrorCode.CONNECTION_ERROR);
		}

		return result;
	}

	public static ServiceResult post(String url, Map<String, Object> params, Object data) {
		ServiceResult result;
		try {
			// TODO: check something before call service
			result = restTemplate.postForObject(url, data, ServiceResult.class, params);
		} catch (Exception e) {
			result = ServiceHelper.error(ServiceErrorCode.CONNECTION_ERROR);
		}

		return result;
	}

//	public static boolean isSuccess(ServiceResult result) {
//		return ServiceResult.SUCCESS.equals(result.getType());
//	}

	public static ServiceResult success(String value) {
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setType(ServiceResult.SUCCESS);
		serviceResult.setValue(value);
		return serviceResult;
	}

	public static ServiceResult error(String errorCode) {
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setType(ServiceResult.ERROR);
		serviceResult.setValue(errorCode);
		return serviceResult;
	}

	public static ServiceResult error(String errorCode, String description) {
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setType(ServiceResult.ERROR);
		serviceResult.setValue(errorCode);
		serviceResult.setDescription(description);
		return serviceResult;
	}
}
