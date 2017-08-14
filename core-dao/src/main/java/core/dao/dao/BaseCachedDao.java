package core.dao.dao;

import java.util.List;

import javax.persistence.Query;

import core.common.query.PagingInfo;
import core.common.query.SortInfo;
import core.dao.entities.BaseCachedEntity;
import core.dao.query.QueryBuilder;

public class BaseCachedDao<E extends BaseCachedEntity> extends BaseDao<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public void delete(Object id) {
		E entity = find(id);
		entity.setDeleted(true);
		getEm().merge(entity);
	}
	
	public void hardDelete(long id) {
		super.delete(id);
	}
	
	@Override
	public long deleteBy(String name, Object value) {
		QueryBuilder builder = new QueryBuilder();
		builder.append("UPDATE ").append(getClassName())
		.append("  SET deleted=TRUE WHERE ").append(name).append(" = :value", "value", value);
		
		Query query = builder.build(getEm());
		return query.executeUpdate();
	}
	
	public void hardDeleteBy(String name, Object value) {
		super.deleteBy(name, value);
	}

	@Override
	//@Transactional
	public long deleteAllData() {
		String strQuery = " UPDATE " + getClassName() + " SET deleted=TRUE";
		Query query = getEm().createQuery(strQuery);
		return query.executeUpdate();
	}
	
	public void hardDeleteAllData() {
		super.deleteAllData();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAllDataByColumns(String[] names, Object[] values, SortInfo sortInfo, PagingInfo paging) {
		String className = getClassName();

		QueryBuilder builder = new QueryBuilder()
				.append("SELECT e FROM ").append(className).append(" e").append(" WHERE e.deleted=FALSE");
		int i = 0;
		for (String name : names) {
			Object value = values[i++];
			if (value == null) {
				builder.append(" AND e.").append(name).append(" is null");
			} else {
				builder.append(" AND e.").append(name).append(" = :"+name, name, value);
			}
		}
		
		if (sortInfo != null) {
			builder.append(sortInfo);
		}
		
		Query query = builder.build(getEm(), paging);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAllDataByColumn(String name, Object[] values, SortInfo sortInfo, PagingInfo paging) {
		String className = getClassName();

		QueryBuilder builder = new QueryBuilder()
				.append("SELECT e FROM ").append(className).append(" e").append(" WHERE e.deleted=FALSE");
		for (int i = 0; i < values.length; i++) {
            if (i == 0) {
                if (values[i] == null) {
                    builder.append(" WHERE e.").append(name).append(" is null");
                } else {
                    builder.append(" WHERE e.").append(name).append(" = :"+name+i, name+i, values[i]);
                }
            } else {
                if (values[i] == null) {
                    builder.append(" OR e.").append(name).append(" is null");
                } else {
                    builder.append(" OR e.").append(name).append(" = :"+name+i, name+i, values[i]);
                }
            }
        }
		
		if (sortInfo != null) {
			builder.append(sortInfo);
		}
		
		Query query = builder.build(getEm(), paging);
		return query.getResultList();
	}
}
