package core.dao.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.util.StringUtils;

import core.dao.entities.IEntity;

public class BaseDao<E extends IEntity> implements IDao, Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;
	private Class<E> persistentClass;

	public void delete(Object id) {
		E entity = find(id);
		getEm().remove(entity);
	}
	
	public long deleteBy(String name, Object value) {
		StringBuilder strQuery = new StringBuilder(" DELETE FROM ").append(getClassName()).append(" WHERE ")
				.append(name).append(" = ").append(formatParam(value));
		Query query = getEm().createQuery(strQuery.toString());
		return query.executeUpdate();
	}

	public long deleteAllData() {
		String strQuery = " DELETE FROM " + getClassName();
		Query query = getEm().createQuery(strQuery);
		return query.executeUpdate();
	}

	public void create(E entity) {
		getEm().persist(entity);
	}

	public void update(E entity) {
		getEm().merge(entity);
	}

	public E find(Object id) {
		return getEm().find(getPersistentClass(), id);
	}

	public E find(String name, Object value) {
		List<E> list = getAllDataByColumn(name, value);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public E find(String[] names, Object[] values) {
		List<E> list = getAllDataByColumns(names, values);

		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<E> getAllData() {
		return getAllDataWithOrder(null, -1, -1);
	}

	public List<E> getAllData(int firstIndex, int maxResult) {
		return getAllDataWithOrder(null, firstIndex, maxResult);
	}

	public List<E> getAllData(List<String> orderColumns) {
		return getAllDataWithOrder(orderColumns, -1, -1);
	}

	public List<E> getAllData(String... orderColumns) {
		return getAllDataWithOrder(Arrays.asList(orderColumns), -1, -1);
	}

	public void clearCache() {
		getEm().getEntityManagerFactory().getCache().evictAll();
		getEm().clear();
	}

	public List<E> getAllData(List<String> orderColumns, int firstIndex, int maxResult) {
		return getAllDataWithOrder(orderColumns, firstIndex, maxResult);
	}

	public List<E> getAllDataByColumn(String name, Object value, String... orderColumns) {
		return getAllDataByColumns(new String[] { name }, new Object[] { value }, orderColumns);
	}

	public List<E> getAllDataByColumns(String[] names, Object[] values, String... orderColumns) {
		Class<E> entityClass = getPersistentClass();
		String className = getClassName();

		String strQuery = "SELECT e " + "FROM " + className + " e WHERE e.";
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
	
	public List<E> getAllDataByColumns(String name, Object[] values) {
		Class<E> entityClass = getPersistentClass();
		String className = getClassName();

		String strQuery = "SELECT e " + "FROM " + className + " e";
		
		if (values.length > 0) {
			strQuery += " WHERE " + name + " IN (" + org.apache.commons.lang3.StringUtils.join(values, ",") + ")";
		}

		TypedQuery<E> query = getEm().createQuery(strQuery, entityClass);
		return query.getResultList();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	protected String getClassName() {
		return getPersistentClass().getSimpleName();
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getPersistentClass() {
		if (persistentClass == null) {
			persistentClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	protected List<E> getAllDataWithOrder(List<String> orderColumns, int firstIndex, int maxResult) {
		String className = getClassName();
		String strQuery = " SELECT  e " + " FROM " + className + " e ";
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

	protected Object formatParam(Object value) {
		if (value instanceof Integer) {
			return value;
		} else if (value instanceof String) {
			return new StringBuilder("'").append(value).append("'").toString();
		} else if (value instanceof Date) {
			return ((Date) value).getTime();
		} else {
			return value;
		}
	}
}
