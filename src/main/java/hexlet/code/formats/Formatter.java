package hexlet.code.formats;

import hexlet.code.Change;

import java.util.Map;

import static hexlet.code.Constants.Formats.PLAIN;
import static hexlet.code.Constants.Formats.STYLISH;
import static hexlet.code.Constants.Formats.JSON;

public class Formatter {

    public static String format(Map<String, Change> map, String format) throws Exception {
        String result;
        switch (format) {
            case STYLISH:
                result = Stylish.formatToStylish(map);
                break;
            case PLAIN:
                result = Plain.formatToStylish(map);
                break;
            case JSON:
                result = Json.formatToJson(map);
                break;
            default:
                throw new Exception(String
                        .format("%s format is not correct. Please, request %s or %s or %s result formats.",
                                format, STYLISH, PLAIN, JSON));
        }
        return result;
    }
}
