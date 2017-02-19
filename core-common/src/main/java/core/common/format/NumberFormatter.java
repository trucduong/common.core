package core.common.format;

import java.text.DecimalFormat;

public class NumberFormatter implements Formatter<Number> {

	public static final char DOT = '.';
	public static final char COMMA = ',';

	private static final String A = "#,##0";
	private static final String B = "#,##0.00";
	private static final String C = "0";
	private static final String D = "0.00";
	private static final String E = "#,##0_);(#,##0)";
	private static final String COMMA_SEPERATED = "###,###.###";

	private static final String CA = "$#,##0_);($#,##0)";
	private static final String CB = "$#,##0.00_);[Red]($#,##0.00)";

	private DecimalFormat formatter;

	private NumberFormatter(String pattern) {
		formatter = new DecimalFormat(pattern);
	}

	@Override
	public String format(Number source, String... defaultVal) {
		String result = null;
		try {
			result = formatter.format(source);
		} catch (Exception e) {
			if (defaultVal != null && defaultVal.length > 0) {
				result = defaultVal[0];
			}
		}
		return result;
	}

	@Override
	public Number parse(String source, Number... defaultVal) {
		Number result = null;
		try {
			result = formatter.parse(source);
		} catch (Exception e) {
			if (defaultVal != null && defaultVal.length > 0) {
				result = defaultVal[0];
			}
		}
		return result;
	}

	public static NumberFormatter A(char... separators) {
		String pattern = A;
		if (separators != null && separators.length > 0) {
			pattern = A.replace(DOT, separators[0]);
		}

		return new NumberFormatter(pattern);
	}

	public static NumberFormatter B(char... separators) {
		String pattern = B;
		if (separators != null && separators.length > 0) {
			pattern = B.replace(DOT, separators[0]);
		}

		return new NumberFormatter(pattern);
	}

	public static NumberFormatter C(char... separators) {
		String pattern = C;
		if (separators != null && separators.length > 0) {
			pattern = C.replace(DOT, separators[0]);
		}

		return new NumberFormatter(pattern);
	}

	public static NumberFormatter D(char... separators) {
		String pattern = D;
		if (separators != null && separators.length > 0) {
			pattern = D.replace(DOT, separators[0]);
		}

		return new NumberFormatter(pattern);
	}

	public static NumberFormatter E(char... separators) {
		String pattern = E;
		if (separators != null && separators.length > 0) {
			pattern = E.replace(COMMA, separators[0]);
		}

		return new NumberFormatter(pattern);
	}

	public static NumberFormatter COMMA_SEPERATED(char... separators) {
		String pattern = COMMA_SEPERATED;
		if (separators != null && separators.length > 0) {
			pattern = COMMA_SEPERATED.replace(COMMA, separators[0]);
		}

		return new NumberFormatter(pattern);
	}

	public static NumberFormatter CA(char... separators) {
		String pattern = CA;
		if (separators != null && separators.length > 0) {
			pattern = CA.replace(COMMA, separators[0]);
		}

		return new NumberFormatter(pattern);
	}

	public static NumberFormatter CB(char... separators) {
		String pattern = CB;
		if (separators != null && separators.length > 0) {
			pattern = CB.replace(COMMA, separators[0]);
		}

		return new NumberFormatter(pattern);
	}

	public static NumberFormatter get(int digits, int decimals, String digitGroupSymbol, String decimalSymbol) {
		StringBuffer pattern = new StringBuffer();
		if (digits > 0) {
			pattern.append(String.valueOf((int)Math.pow(10, digits)).substring(1));
		} else {
			pattern.append("#");
		}

		if (digitGroupSymbol != null) {
			pattern.append(digitGroupSymbol);
			pattern.append("##0");
		}

		if (decimals > 0) {
			if (decimalSymbol == null) {
				pattern.append(DOT);
			} else {
				pattern.append(decimalSymbol);
			}
			pattern.append(String.valueOf((int)Math.pow(10, decimals)).substring(1));
		}

		return new NumberFormatter(pattern.toString());
	}
}
