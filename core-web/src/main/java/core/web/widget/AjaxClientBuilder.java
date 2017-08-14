package core.web.widget;

import java.util.HashMap;
import java.util.Map;

public class AjaxClientBuilder {

	private Map<String, String> maps;
	
	private AjaxClientBuilder() {
		maps = new HashMap<String, String>();
		maps.put("scriptCharset", "'utf-8'");
		//maps.put("dataType", "'jsonp'");
	}
	
	public static AjaxClientBuilder newInstance() {
		return new AjaxClientBuilder();
	}
	
	/**
	 * Specifies the URL to send the request to. Default is the current page
	 * @param value
	 * @return
	 */
	public AjaxClientBuilder url(String value) {
		maps.put("url", "'"+value+"'");
		return this;
	}
	
	/**
	 * Specifies data to be sent to the server
	 * @param value
	 * @return
	 */
	public AjaxClientBuilder data(String value) {
		maps.put("data", value);
		return this;
	}
	
	/**
	 * success(result,status,xhr)
	 * A function to be run when the request succeeds
	 * @param value
	 * @return
	 */
	public AjaxClientBuilder success(String value) {
		maps.put("success", value);
		return this;
	}
	
	/**
	 * error(xhr,status,error)
	 * A function to run if the request fails.
	 * @param value
	 * @return
	 */
	public AjaxClientBuilder error(String value) {
		maps.put("error", value);
		return this;
	}
	
	public String get() {
		return build("GET");
	}
	
	public String post() {
		return build("POST");
	}
	
	public String build(String method) {
		maps.put("type", "'"+method+"'");
		StringBuilder builder = new StringBuilder();
		
		for (String key : maps.keySet()) {
			builder.append(",").append(key).append(":").append(maps.get(key));
		}
		
		return "$.ajax({" + builder.substring(1) + "});";
	}
}
