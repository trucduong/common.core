package core.service.service;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import core.common.exception.CommonException;
import core.common.format.json.JsonFormatter;
import core.service.invoke.ServiceResultHelper;
import core.service.invoke.ServiceResult;
import core.service.utils.ServiceErrorCode;

public abstract class BaseService {
	protected Logger LOGGER = LoggerFactory.getLogger(getThis());

	protected abstract Class<?> getThis();

	private String errorCode;

//	protected void init(String errcode) throws CommonException {
//		if (errcode == null) {
//			throw new CommonException(ServiceErrorCode.UNKNOW_ERROR);
//		}
//		this.errorCode = errcode;
//	}
//	
//	protected void init() throws CommonException {
//		this.init(ServiceErrorCode.UNKNOW_ERROR);
//	}

	protected ServiceResult success(String value) {
		return ServiceResultHelper.success(value);
	}

	protected ServiceResult success(Object obj) {
		String value = JsonFormatter.toJson(obj);
		return success(value);
	}

	protected ServiceResult error(String errorCode) {
		return ServiceResultHelper.error(errorCode);
	}

	protected ServiceResult error(String errorCode, String description) {
		return ServiceResultHelper.error(errorCode, description);
	}
	
	protected ServiceResult error(String errorCode, Object description) {
		return ServiceResultHelper.error(errorCode, JsonFormatter.toJson(description));
	}

	@ExceptionHandler({ CommonException.class })
	public ServiceResult commonException(CommonException ex) {
		LOGGER.error(ex.getErrCode() + ": " + ex.getMessage(), ex);
		//errorCode = null;
		return error(ex.getErrCode(), ex.getMessage());
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
    public ServiceResult methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return error(ServiceErrorCode.PARAMETER_ERROR, ex.getMessage());
    }

	@ExceptionHandler({ Exception.class, PersistenceException.class })
	public ServiceResult exception(Exception ex) {
		LOGGER.error(ex.getMessage(), ex);

		String errCode = errorCode != null ? errorCode : ServiceErrorCode.UNKNOW_ERROR;
		//errorCode = null;
		return error(errCode, ex.getMessage());
	}
	
	protected String getTenant() {
		return "MOK";
	}
}
