package core.common.query;

import java.util.Arrays;
import java.util.Map;

public class RequestParamHelper {

	/**
	 * Format
	 * 
	 * _order=CREATE_DATE:asc,name:des 
	 * _paging=firstIndex,maxRecord
	 * 
	 */
	public static final String PAGING_PARAM = "paging";
	public static final String SORTING_PARAM = "sorting";

	/**
	 * get sort info
	 * 
	 * @param params
	 * @return sort info or null if have not sort info
	 */
	public static SortInfo getSortInfo(Map<String, String[]> params) {
		if (!params.containsKey(SORTING_PARAM)) {
			return null;
		}

		try {
			SortInfo order = new SortInfo();
			String filterStr = Arrays.toString(params.get(SORTING_PARAM));
			filterStr = filterStr.substring(1, filterStr.length()-1);
			String[] filters = filterStr.split(",");
			for (String str : filters) {
				String[] strs = str.split(":");
				order.addOrder(strs[0], strs[1]);
			}

			return order;
		} catch (Exception e) { }
		return null;
	}
	
	public static void setSortInfo(Map<String, Object> params, SortInfo sortInfo) {
		if (sortInfo != null && !sortInfo.isEmpty()) {
			params.put(SORTING_PARAM, sortInfo.toString());
		}
	}
	
	public static void setPagingInfo(Map<String, Object> params, PagingInfo pagingInfo) {
		if (pagingInfo != null) {
			params.put(PAGING_PARAM, pagingInfo.toString());
		}
	}

	public static PagingInfo getPagingInfo(Map<String, String[]> params) {
		if (!params.containsKey(PAGING_PARAM)) {
			return null;
		}

		try {
			PagingInfo paging = new PagingInfo();
			String filterStr = Arrays.toString(params.get(PAGING_PARAM));
			filterStr = filterStr.substring(1, filterStr.length()-1);
			String[] filters = filterStr.split(",");
			paging.setFirstIndex(Integer.parseInt(filters[0]));
			paging.setMaxRecord(Integer.parseInt(filters[1]));
			
			return paging;
		} catch (Exception e) { }
		return null;
	}
}
