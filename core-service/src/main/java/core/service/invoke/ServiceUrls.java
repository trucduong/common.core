package core.service.invoke;

import java.util.HashMap;
import java.util.Map;

public class ServiceUrls {
    private static Map<String, String> maps = new HashMap<String, String>();

    public static String getServiceUrl(String serviceName) {
        String url = maps.get(serviceName);
        return url == null ? "" : url;
    }

    public static void setServiceUrl(String serviceName, String url) {
        maps.put(serviceName, url);
    }
}
