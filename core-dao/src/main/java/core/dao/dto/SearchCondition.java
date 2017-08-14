//package core.dao.dto;
//
//import java.util.List;
//
//public class SearchCondition {
//    public enum CompareType {
//        EQUALS, LIKE, IS_NULL, IS_NOT_NULL
//    }
//
//    private String field;
//    private Object value;
//    private CompareType compareType;
//    private List<SearchCondition> subConditions;
//    
//    private SearchCondition() {
//    }
//    
//    public static SearchCondition newCondition() {
//        return new SearchCondition();
//    }
//    
//    public SearchCondition add(String field, Object value) {
//        return this;
//    }
//    
//    public SearchCondition add(String field, Object value, CompareType compareType) {
//        return this;
//    }
//    
//    public SearchCondition add(String field, Object value) {
//        return this;
//    }
//    
//    public SearchCondition add(String field, Object value, CompareType compareType) {
//        return this;
//    }
//}
