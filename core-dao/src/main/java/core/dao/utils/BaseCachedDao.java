package core.dao.utils;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import core.dao.entities.BaseCachedEntity;

public class BaseCachedDao<E extends BaseCachedEntity> extends BaseDao<E> {
	private static final long serialVersionUID = 1L;
	protected static final String UN_DELETED = " AND (e.deleted=FALSE OR e.deleted IS NULL)";
	protected static final String DELETED = " AND e.deleted=TRUE";

	@Override
	@Transactional
	public void delete(long id) {
		E entity = find(id);
		entity.setDeleted(true);
		merge(entity);
		getEm().flush();
	}
	
	public void hardDelete(long id) {
		super.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBy(String name, Object value) {
		StringBuilder strQuery = new StringBuilder(" UPDATE ").append(getClassName()).append(" SET deleted=TRUE ").append(" WHERE ")
				.append(name).append(" = ").append(formatParam(value));
		Query query = getEm().createQuery(strQuery.toString());
		query.executeUpdate();
	}
	
	public void hardDeleteBy(String name, Object value) {
		super.deleteBy(name, value);
	}

	@Override
	@Transactional
	public void deleteAllData() {
		String strQuery = " UPDATE " + getClassName() + " SET deleted=TRUE";
		Query query = getEm().createQuery(strQuery);
		query.executeUpdate();
	}
	
	public void hardDeleteAllData() {
		super.deleteAllData();
	}

	@Override
	public List<E> getAllDataByColumns(String[] names, Object[] values, String... orderColumns) {
		Class<E> entityClass = getPersistentClass();
		String className = getClassName();

		String strQuery = "SELECT e " + "FROM " + className + " e WHERE e.deleted=FALSE AND e.";
		String andCondition = " AND e.";

		int i = 0;
		for (String name : names) {
			Object value = values[i++];
			if (value == null) {
				strQuery += name + " is null" + andCondition;
			} else {
				strQuery += name + " = ?" + i + andCondition;
			}
		}
		int endIndex = strQuery.lastIndexOf(andCondition);
		strQuery = strQuery.substring(0, endIndex);

		if (orderColumns != null && orderColumns.length > 0) {
			strQuery += " ORDER BY e." + StringUtils.arrayToDelimitedString(orderColumns, ", e.");
		}
		TypedQuery<E> query = getEm().createQuery(strQuery, entityClass);
		int j = 0;
		for (Object value : values) {
			j++;
			if (value != null) {
				query.setParameter(j, value);
			}
		}

		return query.getResultList();
	}
	
	@Override
	public List<E> getAllDataByColumns(String name, Object[] values) {
		Class<E> entityClass = getPersistentClass();
		String className = getClassName();

		String strQuery = "SELECT e " + "FROM " + className + " e WHERE e.deleted=FALSE";
		
		if (values.length > 0) {
			strQuery += " AND " + name + " IN (" + org.apache.commons.lang3.StringUtils.join(values, ",") + ")";
		}

		TypedQuery<E> query = getEm().createQuery(strQuery, entityClass);
		return query.getResultList();
	}

	@Override
	public int deleteAllDataByColumn(String name, Object value) {
		String className = getClassName();
		String strQuery = " UPDATE " + className + " e SET e.deleted=TRUE " + " WHERE e." + name + " = ?1";
		Query query = getEm().createQuery(strQuery).setParameter(1, value);
		return query.executeUpdate();
	}

	@Override
	public int deleteAllDataByColumns(String[] names, Object[] values) {
		String className = getClassName();
		String strQuery = " UPDATE " + className + " e SET e.deleted=TRUE" + " WHERE e.";// +
																			// name
																			// +
																			// "
																			// =
																			// ?1";
		String andCondition = " AND e.";
		int i = 0;
		for (String name : names) {
			Object value = values[i++];
			if (value == null) {
				strQuery += name + " is null" + andCondition;
			} else {
				strQuery += name + " = ?" + i + andCondition;
			}
		}

		int endIndex = strQuery.lastIndexOf(andCondition);
		strQuery = strQuery.substring(0, endIndex);

		Query query = getEm().createQuery(strQuery);
		int j = 0;
		for (Object value : values) {
			j++;
			if (value != null) {
				query.setParameter(j, value);
			}
		}
		return query.executeUpdate();
	}


	@Override
	@SuppressWarnings("unchecked")
	protected List<E> getAllDataWithOrder(List<String> orderColumns, int firstIndex, int maxResult) {
		String className = getClassName();
		String strQuery = " SELECT  e " + " FROM " + className + " e WHERE e.deleted=FALSE";
		if (orderColumns != null && orderColumns.size() > 0) {
			strQuery += " ORDER BY e." + StringUtils.collectionToDelimitedString(orderColumns, ", e.");

		}
		Query query = getEm().createQuery(strQuery);

		// apply paging
		if (firstIndex >= 0 && maxResult > 0) {
			query.setFirstResult(firstIndex);
			query.setMaxResults(maxResult);
		}

		return query.getResultList();
	}
}
