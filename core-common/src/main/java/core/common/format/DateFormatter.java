package core.common.format;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

	public static final char HYPHEN = '-';
	public static final char SLASH = '/';
	public static final char COLON = ':';
	
	
	public static final String ddMMyyyyHHMMSS = "dd/MM/yyyy HH:mm:ss";
	public static final String ddMMyyyy = "dd/MM/yyyy";
	public static final String MMyyyy = "MM/yyyy";
	public static final String Mdyyyy = "M/d/yyyy";
	public static final String Mdyyyyhmm = "M/d/yyyy h:mm";
	public static final String dMMMyyyy = "d-MMM-yyyy";
	public static final String dMMMyy = "d-MMM-yy";
	public static final String dMMM = "d-MMM";
	public static final String MMMyy = "MMM-yy";
	public static final String hmmAMPM = "h:mm a";
	public static final String hmmssAMPM = "h:mm:ss a";
	public static final String hmm = "h:mm";
	public static final String hmmss = "h:mm:ss";

	private static SimpleDateFormat createDateFormat(String pattern) {
//		SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("vi", "VN"));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
		return formatter;
	}
	
	public static String format(String pattern, Date date, String defaultVal) {
		try {
			SimpleDateFormat formatter = createDateFormat(pattern);
			return formatter.format(date);
		} catch (Exception e) {
			return defaultVal;
		}
	}
//
//	@Override
//	public Date parse(String source, Date... defaultVal) {
//		Date result = null;
//		try {
//			result = formatter.parse(source);
//		} catch (Exception e) {
//			if (defaultVal != null && defaultVal.length > 0) {
//				result = defaultVal[0];
//			}
//		}
//		return result;
//	}
}
