package core.common.format.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class JsonIgnoreFilter implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		if (f.getAnnotation(JsonIgnore.class) != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		if (clazz.getAnnotation(JsonIgnore.class) != null) {
			return true;
		}
		
		return false;
	}

}
