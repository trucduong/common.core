package core.common.format;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {

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
	
	private SimpleDateFormat formatter;
	private DateFormatter(String pattern, Locale locale) {
		formatter = new SimpleDateFormat(pattern, locale);
		formatter.setLenient(true);
	}

	@Override
	public String format(Date source, String... defaultVal) {
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
	public Date parse(String source, Date... defaultVal) {
		Date result = null;
		try {
			result = formatter.parse(source);
		} catch (Exception e) {
			if (defaultVal != null && defaultVal.length > 0) {
				result = defaultVal[0];
			}
		}
		return result;
	}

	public static DateFormatter ddMMyyyy(Locale locale, char... separators) {
		String pattern = ddMMyyyy;
		if (separators != null && separators.length > 0) {
			pattern = ddMMyyyy.replace(SLASH, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter MMyyyy(Locale locale, char... separators) {
		String pattern = MMyyyy;
		if (separators != null && separators.length > 0) {
			pattern = MMyyyy.replace(SLASH, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter Mdyyyy(Locale locale, char... separators) {
		String pattern = Mdyyyy;
		if (separators != null && separators.length > 0) {
			pattern = Mdyyyy.replace(SLASH, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter Mdyyyyhmm(Locale locale, char... separators) {
		String pattern = Mdyyyyhmm;
		if (separators != null && separators.length > 0) {
			pattern = Mdyyyyhmm.replace(SLASH, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter dMMMyyyy(Locale locale, char... separators) {
		String pattern = dMMMyyyy;
		if (separators != null && separators.length > 0) {
			pattern = dMMMyyyy.replace(HYPHEN, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter dMMMyy(Locale locale, char... separators) {
		String pattern = dMMMyy;
		if (separators != null && separators.length > 0) {
			pattern = dMMMyy.replace(HYPHEN, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter dMMM(Locale locale, char... separators) {
		String pattern = dMMM;
		if (separators != null && separators.length > 0) {
			pattern = dMMM.replace(HYPHEN, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter MMMyy(Locale locale, char... separators) {
		String pattern = MMMyy;
		if (separators != null && separators.length > 0) {
			pattern = MMMyy.replace(HYPHEN, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter hmmAMPM(Locale locale, char... separators) {
		String pattern = hmmAMPM;
		if (separators != null && separators.length > 0) {
			pattern = hmmAMPM.replace(COLON, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter hmmssAMPM(Locale locale, char... separators) {
		String pattern = hmmssAMPM;
		if (separators != null && separators.length > 0) {
			pattern = hmmssAMPM.replace(COLON, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter hmm(Locale locale, char... separators) {
		String pattern = hmm;
		if (separators != null && separators.length > 0) {
			pattern = hmm.replace(COLON, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter hmmss(Locale locale, char... separators) {
		String pattern = hmmss;
		if (separators != null && separators.length > 0) {
			pattern = hmmss.replace(COLON, separators[0]);
		}
		
		return new DateFormatter(pattern, locale);
	}
	
	public static DateFormatter ddMMyyyyHHMMSS() {
		return new DateFormatter(ddMMyyyyHHMMSS, Locale.US);
	}
}
