package core.dao.search;

import java.util.HashMap;
import java.util.Map;

public class SearchCondition {
	/**
	 * Format
	 * 
	 * _filter=field1:xyz,field2:abc
	 * _order=CREATE_DATE:asc,name:des
	 * _paging=page:2,maxRow:10
	 * 
	 */
	public static final String ORDER_PARAM = "_order";
	public static final String PAGING_PARAM = "_paging";
	public static final String FILTER_PARAM = "_filter";
	
	public SearchCondition() {
		this.filters = new HashMap<>();
	}
	
	private Map<String, String> filters;
	private OrderInfo order;
	private PaggingInfo paging;
	
	public Map<String, String> getFilters() {
		return filters;
	}
	
	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}
	
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	public PaggingInfo getPaging() {
		return paging;
	}
	public void setPaging(PaggingInfo paging) {
		this.paging = paging;
	}
	
	public void addFilter(String key, String value) {
		filters.put(key, value);
	}
	
	public String getFilter(String key) {
		return filters.get(key);
	}
	
	public boolean hasFilter() {
		return filters != null && !filters.isEmpty();
	}
}
