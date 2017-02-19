package core.dao.search;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SearchConditionHelper {
	public static SearchCondition getSearchCondition(HttpServletRequest request) {
		SearchCondition searchInfo = new SearchCondition();
		Map<String, String[]> params = request.getParameterMap();
		if (params.containsKey(SearchCondition.FILTER_PARAM)) {
			String filterStr = Arrays.toString(params.get(SearchCondition.FILTER_PARAM));
			String[] filters = filterStr.split(",");
			for (String str : filters) {
				String[] strs = str.split(":");
				searchInfo.addFilter(strs[0], strs[1]);
			}
		}
		
		OrderInfo order = new OrderInfo();
		if (params.containsKey(SearchCondition.ORDER_PARAM)) {
			String filterStr = Arrays.toString(params.get(SearchCondition.ORDER_PARAM));
			String[] filters = filterStr.split(",");
			for (String str : filters) {
				String[] strs = str.split(":");
				order.addOrder(strs[0], strs[1]);
			}
			searchInfo.setOrder(order);
		}
		
//		PaggingInfo paging = new PaggingInfo()
//		if (params.containsKey(SearchCondition.PAGING_PARAM)) {
//			String filterStr = Arrays.toString(params.get(SearchCondition.PAGING_PARAM));
//			String[] filters = filterStr.split(",");
//			for (String str : filters) {
//				String[] strs = str.split(":");
//				paging.set
//			}
//			searchInfo.setOrder(paging);
//		}
		
		return searchInfo;
	}
}
