package core.dao.query;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class NativeQueryBuilder extends QueryBuilder {
	
	public NativeQueryBuilder() {
		super();
	}
	
	@Override
	protected Query createQuery(EntityManager em) {
		return em.createNativeQuery(strQuery.toString());
	}
}
