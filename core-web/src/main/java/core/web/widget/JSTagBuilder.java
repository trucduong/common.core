package core.web.widget;

import java.util.Map;

public class JSTagBuilder {
	private StringBuilder builder;

	private JSTagBuilder() {
		builder = new StringBuilder();
		builder.append("<script type='text/javascript'>");
		builder.append("$(function() {");
	}

	public static JSTagBuilder newTag() {
		return new JSTagBuilder();
	}
	
	public JSTagBuilder append(String source) {
		builder.append(source);
		return this;
	}

	public String build() {
		builder.append("});</script>");
		return builder.toString();
	}
	
	public static String convertToJSObject(Map<String, String> params) {
		if (params.isEmpty()) {
			return "{}";
		}

		StringBuilder builder = new StringBuilder();
		for (String key : params.keySet()) {
			builder.append(",");
			builder.append(key);
			builder.append(":");
			builder.append("'");
			builder.append(params.get(key));
			builder.append("'");
		}

		return "{" + builder.substring(1) + "}";
	}
	
	public static String convertToJSObject(Object... params) {
		if (params.length == 0) {
			return "{}";
		}

		StringBuilder builder = new StringBuilder();
		int i = 0;
		for (Object obj : params) {
			if (i % 2 == 0) {
				builder.append(",");
				builder.append(obj);
				builder.append(":");
			} else {
				builder.append("'");
				builder.append(obj);
				builder.append("'");
			}
			i++;
		}

		return "{" + builder.substring(1) + "}";
	}
}
