package hexlet.code;

import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.util.LinkedHashMap;

public class Formatter {

    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";

    public static String format(LinkedHashMap<String, Object> map, String format){
        if (format.equalsIgnoreCase(STYLISH)) {
            return Stylish.formatToStylish(map);
        } else if (format.equalsIgnoreCase(PLAIN)) {
            return Plain.formatToStylish(map);
        }
        return "";
    }
}
