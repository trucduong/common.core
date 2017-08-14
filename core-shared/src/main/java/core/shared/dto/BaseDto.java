package core.shared.dto;

public abstract class BaseDto {
	public static final String ID = "id";
	public static final String STATUS = "status";
	
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
