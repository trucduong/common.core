package core.dao.dao;

import javax.persistence.EntityManager;

public interface IDao {
	public void setEm(EntityManager em);

	public EntityManager getEm();
}
