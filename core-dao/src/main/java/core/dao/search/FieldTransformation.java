package core.dao.search;

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
}
