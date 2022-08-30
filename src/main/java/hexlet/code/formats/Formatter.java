package hexlet.code.formats;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Changes;

import java.util.Map;

import static hexlet.code.MyConstants.Formats.PLAIN;
import static hexlet.code.MyConstants.Formats.STYLISH;
import static hexlet.code.MyConstants.Formats.JSON;

public class Formatter {

    public static String format(Map<String, Changes> map, String format) throws Exception {
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
                throw new Exception("Format is not recognized. Please, try again.");
        }
        return result;
    }
}
