package core.common.exception;

public class CommonException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errCode;

	public String getErrCode() {
		return errCode;
	}
//	
//	@Override
//	public String getMessage() {
//		String message = super.getMessage();
//		if (StringUtils.isEmpty(message)) {
//			message = getLocalizedMessage();
//		}
//		return message;
//	}
//	
//	@Override
//	public String getLocalizedMessage() {
//		// TODO get localized message here
//		return super.getLocalizedMessage();
//	}

	public CommonException(String errCode) {
		super();
		this.errCode = errCode;
	}

	public CommonException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
	}

	public CommonException(String errCode, Throwable cause) {
		super(cause);
		this.errCode = errCode;
	}

	public CommonException(String errCode, String errMsg, Throwable cause) {
		super(errMsg, cause);
		this.errCode = errCode;
	}
}
