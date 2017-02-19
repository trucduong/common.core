package core.dao.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import core.dao.entities.BaseEntity;

public class EntityModifier {

	@SuppressWarnings("unchecked")
	public static <V> V get(BaseEntity object, String fieldName) {
		Class<?> clazz = object.getClass();
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				return (V) field.get(object);
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			} catch (Exception e) {
				// throw new IllegalStateException(e);
				clazz = null;
			}
		}
		return null;
	}

	public static Map<String, Object> get(BaseEntity object, boolean skipMissing) {
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			Class<?> clazz = object.getClass();
			while (clazz != null) {
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					maps.put(field.getName(), field.get(object));
				}
			}
		} catch (Exception e) {
			maps.clear();
		}
		return maps;
	}

	public static Map<String, Object> get(BaseEntity object, String[] fileds, boolean skipMissing) {
		Map<String, Object> maps = new HashMap<String, Object>();
		for (String field : fileds) {
			Object value = get(object, field);
			if (value == null && !skipMissing) {
				maps.clear();
				break;
			} else {
				maps.put(field, value);
			}
		}
		return maps;
	}

	public static boolean set(BaseEntity object, String fieldName, Object fieldValue) {
		Class<?> clazz = object.getClass();
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(object, fieldValue);
				return true;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			} catch (Exception e) {
				// throw new IllegalStateException(e);
				clazz = null;
			}
		}
		return false;
	}

	public static boolean set(BaseEntity object, Map<String, String> maps, boolean skipMissing) {
		for (String key : maps.keySet()) {
			if (!set(object, key, maps.get(key)) && !skipMissing) {
				return false;
			}
		}
		return true;
	}
}
