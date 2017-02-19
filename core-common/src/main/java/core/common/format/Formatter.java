package core.common.format;

public interface Formatter<T> {
	String format(T obj, String... defaultVal);
	T parse(String str, @SuppressWarnings("unchecked") T... defaultVal);
}
