package core.service.error;

public class InvalidValueError implements ServiceError {
	private InvalidValueType type;
	private String fieldName;
	private Object invalidValue;
	private Object validValue;
	private String description;

	private InvalidValueError() {
	}

	private InvalidValueError(String fieldName, Object invalidValue, Object validValue, String description) {
		this.fieldName = fieldName;
		this.invalidValue = invalidValue;
		this.validValue = validValue;
		this.description = description;
	}

	public static InvalidValueError equals(String fieldName, Object invalidValue, Object validValue,
			String description) {
		InvalidValueError error = new InvalidValueError(fieldName, invalidValue, validValue, description);
		error.type = InvalidValueType.EQUALS;
		return error;
	}

	public static InvalidValueError noEquals(String fieldName, Object invalidValue, Object validValue,
			String description) {
		InvalidValueError error = new InvalidValueError(fieldName, invalidValue, validValue, description);
		error.type = InvalidValueType.NOT_EQUALS;
		return error;
	}

	public static InvalidValueError greaterThan(String fieldName, Object invalidValue, Object validValue,
			String description) {
		InvalidValueError error = new InvalidValueError(fieldName, invalidValue, validValue, description);
		error.type = InvalidValueType.GREATER_THAN;
		return error;
	}

	public static InvalidValueError greaterThanOrEquals(String fieldName, Object invalidValue, Object validValue,
			String description) {
		InvalidValueError error = new InvalidValueError(fieldName, invalidValue, validValue, description);
		error.type = InvalidValueType.GREATER_OR_EQUALS;
		return error;
	}

	public static InvalidValueError lessThan(String fieldName, Object invalidValue, Object validValue,
			String description) {
		InvalidValueError error = new InvalidValueError(fieldName, invalidValue, validValue, description);
		error.type = InvalidValueType.LESS_THAN;
		return error;
	}

	public static InvalidValueError lessThanOrEquals(String fieldName, Object invalidValue, Object validValue,
			String description) {
		InvalidValueError error = new InvalidValueError(fieldName, invalidValue, validValue, description);
		error.type = InvalidValueType.LESS_OR_EQUALS;
		return error;
	}

	public InvalidValueType getType() {
		return type;
	}

	public void setType(InvalidValueType type) {
		this.type = type;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getInvalidValue() {
		return invalidValue;
	}

	public void setInvalidValue(Object invalidValue) {
		this.invalidValue = invalidValue;
	}

	public Object getValidValue() {
		return validValue;
	}

	public void setValidValue(Object validValue) {
		this.validValue = validValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
