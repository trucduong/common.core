package core.service.utils;

public class ServiceResult {
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";

	private String type;
	private String value;
	private String description;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSuccess() {
		return SUCCESS.equals(this.type);
	}
}
