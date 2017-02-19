package core.dao.utils;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class NativeQueryBuilder extends QueryBuilder {
	
	public NativeQueryBuilder() {
		super();
	}
	
	@Override
	public Query build(EntityManager em) {
		Query query = em.createNativeQuery(strQuery.toString());
		for (String name : this.params.keySet()) {
			query.setParameter(name, this.params.get(name));
		}
		return query;
	}
}
