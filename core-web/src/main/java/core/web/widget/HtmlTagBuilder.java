package core.web.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlTagBuilder {
    
    public enum TagClosingType {
        FULL, SORT, NO
    }
    
    private List<String> childs;
    private Map<String, String> maps;
    private String tagName;

    private HtmlTagBuilder(String tagName) {
        this.tagName = tagName;
        maps = new HashMap<String, String>();
        childs = new ArrayList<String>();
    }

    public static HtmlTagBuilder newTag(String tagName) {
        return new HtmlTagBuilder(tagName);
    }

    public HtmlTagBuilder set(String attribute, Object value) {
        if (value == null) {
            maps.put(attribute, "");
        } else {
            maps.put(attribute, String.valueOf(value));
        }
        return this;
    }
    
    public HtmlTagBuilder add(String child) {
        childs.add(child);
        return this;
    }

    public String build(TagClosingType closingType) {
        StringBuilder builder = new StringBuilder("<").append(tagName);
        for (String name : maps.keySet()) {
            builder.append(" ").append(name).append("='").append(maps.get(name)).append("'");
        }
        
        switch (closingType) {
        case FULL:
            builder.append(">");
            addChilds(builder);
            builder.append("</").append(tagName).append(">");
            break;
        case SORT:
            builder.append("/>");
            addChilds(builder);
        default:
            builder.append(">");
            addChilds(builder);
        }
        return builder.toString();
    }
    
    private void addChilds(StringBuilder builder) {
        for (String string : childs) {
            builder.append(string);
        }
    }
}
