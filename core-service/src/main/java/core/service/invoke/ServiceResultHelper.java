package core.service.invoke;

import java.lang.reflect.Type;

import core.common.format.json.JsonFormatter;

public class ServiceResultHelper {

	public static <T> T getValue(ServiceResult result, Class<T> classOfT) {
		return JsonFormatter.fromJson(result.getValue(), classOfT);
	}
	
	public static <T> T getValue(ServiceResult result, Type typeOfT) {
		return JsonFormatter.fromJson(result.getValue(), typeOfT);
	}

	public static boolean isSuccess(ServiceResult result) {
		return ServiceResult.SUCCESS.equals(result.getType());
	}

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
