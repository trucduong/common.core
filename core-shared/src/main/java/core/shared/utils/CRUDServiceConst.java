package core.shared.utils;

public abstract class CRUDServiceConst {
	public static final String PARAM_ID = "id";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_VALUE = "value";
	public static final String PARAM_VALUES = "values";
	public static final String PARAM_ACTION = "action";
	public static final String PARAM_ACTION_VALUE = "actionValue";
	public static final String ACTION_SEARCH = "search";
	
	public static final String READ = "/read"; // ?id=xxx
	public static final String READ_ALL = "/readall"; // _order=name:asc&_paging=1,3
	public static final String READ_BY = "/readby"; // ?name=xxx&value=xxx
//	public static final String READ_ALL_BY = "/read-all-by/{name}/{values}";
	public static final String COUNT_ALL = "/countall";
	
	public static final String CREATE = "/create";
	public static final String UPDATE = "/update/{id}"; // post only
	public static final String UPDATE_ATTRIBUTE = "/update_att/{id}"; // post only
	public static final String DELETE = "/delete/{id}";  // post only
	
	
}
