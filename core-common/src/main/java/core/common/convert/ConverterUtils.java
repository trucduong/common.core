package core.common.convert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import core.common.format.DateFormatter;

public class ConverterUtils {
	public static int toInt(Object obj, int defaultVal) {
		try {
			if (obj instanceof Integer) {
				return (int) obj;
			}
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
		}
		return defaultVal;
	}

	public static int toInt(Object obj) {
		return toInt(obj, 0);
	}

	public static long toLong(Object obj, long defaultVal) {
		try {
			if (obj instanceof Long) {
				return (Long) obj;
			}
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
		}
		return defaultVal;
	}

	public static long toLong(Object obj) {
		return toLong(obj, 0);
	}

	public static String toString(Object obj, String defaultVal) {
		try {
			if (obj == null) {
				return defaultVal;
			}
			return String.valueOf(obj);
		} catch (Exception e) {
		}
		return defaultVal;
	}

	public static String toString(Object obj) {
		return toString(obj, "");
	}

	public static BigDecimal toBigDecimal(Object obj, BigDecimal defaultVal) {
		try {
			if (obj instanceof BigDecimal) {
				return (BigDecimal) obj;
			}
			return new BigDecimal(obj.toString());
		} catch (Exception e) {
		}
		return defaultVal;
	}

	public static BigDecimal toBigDecimal(Object obj) {
		return toBigDecimal(obj, BigDecimal.ZERO);
	}

	public static Double toDouble(Object obj, Double defaultVal) {
		try {
			if (obj instanceof Double) {
				return (Double) obj;
			}
			return Double.parseDouble(String.valueOf(obj));
		} catch (Exception e) {
		}
		return defaultVal;
	}

	public static Double toDouble(Object obj) {
		return toDouble(obj, 0.0);
	}

	public static Date toDate(Object obj, String format, Date defaultVal) {
		try {
			if (obj instanceof Date) {
				return (Date) obj;
			}
			return DateFormatter.ddMMyyyy(Locale.ENGLISH, null).parse(String.valueOf(obj), defaultVal);
		} catch (Exception e) {
		}
		return defaultVal;
	}

	public static Date toDate(Object obj) {
		return toDate(obj, DateFormatter.ddMMyyyy, null);
	}
}
