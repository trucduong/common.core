package core.dao.entities;

import core.shared.dto.BaseDto;

public interface IEntity {

	public Object getEntityId();
	public void setEntityId(Object id);
	public void bind(BaseDto dto);
	public void unBind(BaseDto dto);
}
