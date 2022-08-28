package hexlet.code.formats;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Changes;

import java.util.Map;

public class Formatter {

    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    public static String format(Map<String, Changes> map, String format) throws JsonProcessingException {
        String result = "";
        if (format.equalsIgnoreCase(STYLISH)) {
            result = Stylish.formatToStylish(map);
        } else if (format.equalsIgnoreCase(PLAIN)) {
            result = Plain.formatToStylish(map);
        } else if (format.equalsIgnoreCase(JSON)) {
            result = Json.formatToJson(map);
        }
        return result;
    }
}
