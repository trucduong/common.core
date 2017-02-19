package core.dao.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import core.common.convert.Converter;
import core.common.reflect.ObjectModifier;

public class DaoUtils {

	public static <T> T selectFirst(EntityManager entityManager, QueryBuilder builder, Class<T> cls, String[] columns) {
		return selectFirst(entityManager, builder, cls, columns, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> T selectFirst(EntityManager entityManager, QueryBuilder builder, Class<T> cls, String[] columns,
			Map<String, Converter<?>> converters) {
		List<Object[]> resultList = builder.build(entityManager).getResultList();
		if (resultList.isEmpty()) {
			return null;
		}

		return convert(cls, columns, resultList.get(0), converters);
	}

	public static <T> List<T> selectAll(EntityManager entityManager, QueryBuilder builder, Class<T> cls,
			String[] columns) {
		return selectAll(entityManager, builder, cls, columns, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> selectAll(EntityManager entityManager, QueryBuilder builder, Class<T> cls,
			String[] columns, Map<String, Converter<?>> converters) {
		List<T> list = new ArrayList<T>();
		List<Object[]> resultList = builder.build(entityManager).getResultList();
		if (resultList.isEmpty()) {
			return list;
		}

		for (Object[] objects : resultList) {
			T obj = convert(cls, columns, objects, converters);
			if (obj != null) {
				list.add(obj);
			}
		}
		return list;
	}

	public static <T> T convert(Class<T> cls, String[] columns, Object[] objects,
			Map<String, Converter<?>> converters) {
		T obj = null;
		try {
			obj = cls.newInstance();
			if (converters == null) {
				for (int i = 0; i < columns.length && i < objects.length; i++) {
					ObjectModifier.set(obj, columns[i], objects[i]);
				}
			} else {
				for (int i = 0; i < columns.length && i < objects.length; i++) {
					Converter<?> converter = converters.get(columns[i]);
					if (converter != null) {
						ObjectModifier.set(obj, columns[i], converter.convert(objects[i]));
					} else {
						ObjectModifier.set(obj, columns[i], objects[i]);
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
		}
		return obj;
	}

	public static boolean isValidId(Long id) {
		return id != null && id > 0;
	}
}
