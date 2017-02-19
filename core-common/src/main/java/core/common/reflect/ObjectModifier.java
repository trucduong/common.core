package core.common.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectModifier {

	@SuppressWarnings("unchecked")
	public static <V> V get(Object object, String fieldName) {
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

	@SuppressWarnings("unchecked")
	public static <V> V get(Object object, Field field) {
		try {
			field.setAccessible(true);
			return (V) field.get(object);
		} catch (Exception e) {
		}
		return null;
	}

	public static Map<String, Object> get(Object object, boolean skipMissing) {
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

	public static Map<String, Object> get(Object object, String[] fileds, boolean skipMissing) {
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

	public static boolean set(Object object, String fieldName, Object fieldValue) {
		Class<?> clazz = object.getClass();
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				return set(object, field, fieldValue);
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			} catch (Exception e) {
				// throw new IllegalStateException(e);
				clazz = null;
			}
		}
		return false;
	}

	public static boolean set(Object object, Field field, Object fieldValue) {
		try {
			field.setAccessible(true);
			field.set(object, fieldValue);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean set(Object object, Map<String, Object> maps, boolean skipMissing) {
		for (String key : maps.keySet()) {
			if (!set(object, key, maps.get(key)) && !skipMissing) {
				return false;
			}
		}
		return true;
	}

	public static int bind(Object a, Object b) {
		int count = 0;

		if (a == null || b == null) {
			return count;
		}

		try {
			Field fields[] = a.getClass().getFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (set(b, field.getName(), field.get(a))) {
					count++;
				}
			}
		} catch (Exception e) {
		}

		return count;
	}
}
