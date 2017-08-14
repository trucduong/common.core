package core.dao.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import core.common.query.PagingInfo;
import core.common.query.SortInfo;
import core.dao.entities.IEntity;
import core.dao.query.QueryBuilder;

public class BaseDao<E extends IEntity> implements IDao, Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;
    private Class<E> persistentClass;

    public void delete(Object id) {
        E entity = find(id);
        if (entity != null) {
            getEm().remove(entity);
        }
    }

    public long deleteBy(String name, Object value) {
        QueryBuilder builder = new QueryBuilder();
        builder.append("DELETE FROM ").append(getClassName()).append(" WHERE ").append(name).append(" = :value",
                "value", value);
        Query query = builder.build(getEm());
        return query.executeUpdate();
    }

    public long deleteAllData() {
        QueryBuilder builder = new QueryBuilder();
        builder.append("DELETE FROM ").append(getClassName());
        Query query = builder.build(getEm());
        return query.executeUpdate();
    }

    public void create(E entity) {
        getEm().persist(entity);
    }

    public E update(E entity) {
        return getEm().merge(entity);
    }

    public E find(Object id) {
        return getEm().find(getPersistentClass(), id);
    }

    public List<E> getAllDataByColumn(String name, Object value) {
        return getAllDataByColumn(name, value, null, null);
    }

    public List<E> getAllDataByColumn(String name, Object value, SortInfo sortInfo, PagingInfo pagingInfo) {
        List<E> list = getAllDataByColumn(name, new Object[] { value }, sortInfo, pagingInfo);
        return list;
    }

    public List<E> getAllData() {
        return getAllData(null, null);
    }

    public List<E> getAllDataWithPaging(PagingInfo pagingInfo) {
        return getAllData(null, pagingInfo);
    }

    public List<E> getAllDataWithSorting(SortInfo sortInfo) {
        return getAllData(sortInfo, null);
    }

    public List<E> getAllData(SortInfo sortInfo, PagingInfo pagingInfo) {
        String[] empty = new String[] {};
        return getAllDataByColumns(empty, empty, sortInfo, pagingInfo);
    }

    public void clearCache() {
        getEm().getEntityManagerFactory().getCache().evictAll();
        getEm().clear();
    }

    @SuppressWarnings("unchecked")
    public List<E> getAllDataByColumns(String[] names, Object[] values, SortInfo sortInfo, PagingInfo pagingInfo) {
        String className = getClassName();

        QueryBuilder builder = new QueryBuilder().append("SELECT e FROM ").append(className).append(" e")
                .append(" WHERE 1=1");
        int i = 0;
        for (String name : names) {
            Object value = values[i++];
            if (value == null) {
                builder.append(" AND e.").append(name).append(" is null");
            } else {
                builder.append(" AND e.").append(name).append(" = :" + name, name, value);
            }
        }

        if (sortInfo != null) {
            builder.append(sortInfo);
        }

        Query query = builder.build(getEm(), pagingInfo);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<E> getAllDataByColumn(String name, Object[] values, SortInfo sortInfo, PagingInfo pagingInfo) {
        String className = getClassName();

        QueryBuilder builder = new QueryBuilder().append("SELECT e FROM ").append(className).append(" e");
        for (int i = 0; i < values.length; i++) {
            if (i == 0) {
                if (values[i] == null) {
                    builder.append(" WHERE e.").append(name).append(" is null");
                } else {
                    builder.append(" WHERE e.").append(name).append(" = :" + name + i, name + i, values[i]);
                }
            } else {
                if (values[i] == null) {
                    builder.append(" OR e.").append(name).append(" is null");
                } else {
                    builder.append(" OR e.").append(name).append(" = :" + name + i, name + i, values[i]);
                }
            }
        }

        if (sortInfo != null) {
            builder.append(sortInfo);
        }

        Query query = builder.build(getEm(), pagingInfo);
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
}
