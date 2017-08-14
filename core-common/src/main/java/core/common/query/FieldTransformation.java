package core.common.query;

import java.util.HashMap;
import java.util.Map;

public abstract class FieldTransformation {

	protected abstract Map<String, String> getFieldMappings();
	
	public String transform(String field) {
		Map<String, String> maps = getFieldMappings();
		if (maps.containsKey(field)) {
			return maps.get(field);
		}
		
		return field;
	}
	
	public static FieldTransformation getDefault() {
		return new FieldTransformation() {
			@Override
			protected Map<String, String> getFieldMappings() {
				return new HashMap<String, String>();
			}
		};
	}
}
