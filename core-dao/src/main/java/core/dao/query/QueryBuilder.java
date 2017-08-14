package core.dao.query;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import core.common.query.FieldTransformation;
import core.common.query.PagingInfo;
import core.common.query.SortInfo;

public class QueryBuilder {
	protected Map<String, Object> params;
	protected StringBuilder strQuery;
	protected PagingInfo paging;

	public QueryBuilder() {
		this.params = new HashMap<String, Object>();
		this.strQuery = new StringBuilder();
	}

	public QueryBuilder append(String statement) {
		strQuery.append(statement);
		return this;
	}

	public QueryBuilder append(String statement, Object... params) {
		strQuery.append(statement);
		if (params != null && params.length > 0) {
			int i = 0;
			do {
				addParam((String) params[i++], params[i++]);
			} while (i < params.length);
		}
		return this;
	}
	
	public QueryBuilder append(SortInfo order) {
		return append(order, "", FieldTransformation.getDefault());
	}
	
	public QueryBuilder append(SortInfo order, String prefix) {
		return append(order, prefix, FieldTransformation.getDefault());
	}
	
	public QueryBuilder append(SortInfo order, String prefix, FieldTransformation transformer) {
		if (order != null && !order.isEmpty()) {
			strQuery.append(" ORDER BY ");
			for (String field : order.getFields()) {
				strQuery.append(prefix + transformer.transform(field)).append(" ")
				.append(order.getOrder(field)).append(",");
			}
			strQuery.deleteCharAt(strQuery.length()-1);
		}
		
		return this;
	}
	
	public QueryBuilder setPaging(PagingInfo paging) {
		this.paging = paging;
		return this;
	}

	public QueryBuilder addParam(String name, Object value) {
		this.params.put(name, value);
		return this;
	}

	public Query build(EntityManager em) {
		return build(em, paging);
	}
	
	protected Query createQuery(EntityManager em) {
		return em.createQuery(strQuery.toString());
	}
	
	public Query build(EntityManager em, PagingInfo paging) {
		Query query = createQuery(em);
		for (String name : this.params.keySet()) {
			query.setParameter(name, this.params.get(name));
		}
		
		if (paging != null) {
			query.setFirstResult(paging.getFirstIndex());
			query.setMaxResults(paging.getMaxRecord());
		}
		
		return query;
	}

}
