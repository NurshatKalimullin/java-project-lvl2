package hexlet.code.formats;

import hexlet.code.Change;

import java.util.List;
import java.util.Map;

import static hexlet.code.Constants.Statuses.*;

public class Plain {

    public static String formatToStylish(Map<String, Change> map) throws Exception {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Change> entry : map.entrySet()) {
            String status = entry.getValue().getStatus();
            switch (status) {
                case CHANGED:
                    result.append("Property '")
                            .append(entry.getKey())
                            .append("' was updated. From ")
                            .append(formatValue(entry.getValue().getOldValue()))
                            .append(" to ")
                            .append(formatValue(entry.getValue().getNewValue()))
                            .append("\n");
                    break;
                case DELETED:
                    result.append("Property '")
                            .append(entry.getKey())
                            .append("' was removed")
                            .append("\n");
                    break;
                case ADDED:
                    result.append("Property '")
                            .append(entry.getKey())
                            .append("' was added with value: ")
                            .append(formatValue(entry.getValue().getNewValue()))
                            .append("\n");
                    break;
                case UNCHANGED:
                    break;
                default:
                    throw new Exception("Unexpected status:" + status);
            }
        }
        result = new StringBuilder(result.substring(0, result.length() - 1));
        return result.toString();
    }


    private static String formatValue(Object value) {
        String result;
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        } else {
            if (value instanceof String) {
                result = "'" + value + "'";
            } else {
                result = String.valueOf(value);
            }
        }
        return result;
    }
}
