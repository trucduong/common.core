package core.dao.dto;

import java.util.Date;
import java.util.HashMap;

public class ResultMap extends HashMap<String, Object>{
	private static final long serialVersionUID = 5182827722811871488L;
	public static final int DEFAULT_INT = 0;
	public static final String DEFAULT_STRING = null;

	public int getInt(String key) {
		return getInt(key, DEFAULT_INT);
	}
	
	public int getInt(String key, int defaultValue) {
		try {
			Integer result = (Integer) this.get(key);
			if (result != null) {
				return result.intValue();
			}
		} catch (Exception e) {	}
		
		return defaultValue;
	}
	
	public String getString(String key) {
		return getString(key, DEFAULT_STRING);
	}
	
	public String getString(String key, String defaultValue) {
		try {
			String result = (String) this.get(key);
			if (result != null) {
				return result;
			}
		} catch (Exception e) {	}
		
		return defaultValue;
	}
	
	public Date getDate(String key) {
		return getDate(key, null);
	}
	
	public Date getDate(String key, Date defaultValue) {
		try {
			Date result = (Date) this.get(key);
			if (result != null) {
				return result;
			}
		} catch (Exception e) {	}
		
		return defaultValue;
	}
}
