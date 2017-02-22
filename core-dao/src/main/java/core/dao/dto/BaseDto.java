package core.dao.dto;

public abstract class BaseDto {
	protected Object id;

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}
}
