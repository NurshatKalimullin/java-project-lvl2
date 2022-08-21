package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formats.Json;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.util.LinkedHashMap;

public class Formatter {

    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    public static String format(LinkedHashMap<String, Object> map, String format) throws JsonProcessingException {
        String result = "";
        if (format.equalsIgnoreCase(STYLISH)) {
            result = Stylish.formatToStylish(map);
        } else if (format.equalsIgnoreCase(PLAIN)) {
            result = Plain.formatToStylish(map);
        } else if (format.equalsIgnoreCase(JSON)) {
            result = Json.formatToJson(map);
        }
        System.out.println(result);
        return result;
    }
}
