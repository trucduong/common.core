package core.service.action;

public abstract class CRUDServiceActionConst {
	public static final String PARAM_ID = "id";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_VALUE = "value";
	public static final String PARAM_VALUES = "values";
	
	public static final String READ = "/read/{id}";
	public static final String READ_ALL = "/read-all";
	public static final String READ_BY = "/read-by/{name}/{value}";
	public static final String READ_ALL_BY = "/read-all-by/{name}/{values}";
	
	public static final String CREATE = "/create";
	public static final String UPDATE = "/update/{id}";
	public static final String DELETE = "/delete/{id}";
	
	
}
