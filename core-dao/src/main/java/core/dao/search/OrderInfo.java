package core.dao.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderInfo {
	
	public enum OrderType {
		ASC, DESC
	}

	private Map<String, OrderType> orders;
	
	public OrderInfo() {
		orders = new HashMap<String, OrderType>();
	}
	
	public Set<String> getFields() {
		return orders.keySet();
	}
	
	public String getOrder(String field) {
		if (orders.containsKey(field)) {
			return orders.get(field).toString();
		}
		
		return null;
	}
	
	public void addOrder(String field, String orderType) {
		OrderType type = null;
		
		orders.put(field, type);
	}
	
	public boolean isEmpty() {
		return orders.isEmpty();
	}
	
	public void clearAll() {
		this.orders.clear();
	}
}
