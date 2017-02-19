package core.common.format.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonFormatter {

	public static String toJson(Object src) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter())
//				.setDateFormat("dd/MM/YYYY HH:mm:ss")
				.create();
		return gson.toJson(src);
	}
	
	public static <T> T fromJson(String json, Class<T> classOfT) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter())
//				.setDateFormat("dd/MM/YYYY HH:mm:ss")
				.create();
		return gson.fromJson(json, classOfT);
	}
	
	public static <T> T fromJson(String json, Type typeOfT) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter())
//				.setDateFormat("dd/MM/YYYY HH:mm:ss")
				.create();
		return gson.fromJson(json, typeOfT);
	}
}
