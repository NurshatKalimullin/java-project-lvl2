package hexlet.code;

import hexlet.code.formats.Stylish;

import java.util.LinkedHashMap;

public class Formatter {

    private static final String STYLISH = "stylish";

    public static String format(LinkedHashMap<String, Object> map, String format){
        if (format.equalsIgnoreCase(STYLISH)) {
            return Stylish.formatToStylish(map);
        }
        return "";
    }
}
