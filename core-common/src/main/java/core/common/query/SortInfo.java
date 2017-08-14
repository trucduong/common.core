package core.common.query;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SortInfo {
	
	public class OrderType {
		public static final String ASC = "ASC"; 
		public static final String DESC = "DESC";
	}

	private Map<String, String> orders;
	
	public SortInfo() {
		orders = new HashMap<String, String>();
	}
	
	public Set<String> getFields() {
		return orders.keySet();
	}
	
	public String getOrder(String field) {
		if (orders.containsKey(field)) {
			return orders.get(field);
		}
		
		return null;
	}
	
	public void addOrder(String field, String orderType) {
		orders.put(field, orderType);
	}
	
	public boolean isEmpty() {
		return orders.isEmpty();
	}
	
	public void clearAll() {
		this.orders.clear();
	}
	
	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		for (String field : getFields()) {
			builder.append(",");
			builder.append(field).append(":").append(getOrder(field));
			
		}
		return builder.substring(1);
	}
}
