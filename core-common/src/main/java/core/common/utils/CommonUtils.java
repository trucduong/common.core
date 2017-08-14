package core.common.utils;

import java.util.UUID;

public class CommonUtils {
    public static String concat(String... str) {
        StringBuilder builder = new StringBuilder();
        for (String s : str) {
            if (s != null) {
                builder.append(s);
            }
        }

        return builder.toString();
    }

    public static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
