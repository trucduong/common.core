package core.dao.entities;

import core.dao.dto.BaseDto;

public interface IEntity {

	public void bind(BaseDto dto);
	public void unBind(BaseDto dto);
}
