package hexlet.code.formats;

import hexlet.code.Changes;

import java.util.Map;

import static hexlet.code.MyConstants.Statuses.CHANGED;
import static hexlet.code.MyConstants.Statuses.DELETED;
import static hexlet.code.MyConstants.Statuses.ADDED;

public class Plain {

    public static String formatToStylish(Map<String, Changes> map) {
        String result = "";

        for (Map.Entry<String, Changes> entry : map.entrySet()) {
            if (entry.getValue().getStatus().equalsIgnoreCase(CHANGED)) {
                result = result + "Property '"
                        + entry.getKey()
                        + "' was updated. From "
                        + formatValue(entry.getValue().getOldValue())
                        + " to "
                        + formatValue(entry.getValue().getNewValue())
                        + "\n";
            } else if (entry.getValue().getStatus().equalsIgnoreCase(DELETED)) {
                result = result + "Property '"
                        + entry.getKey()
                        + "' was removed"
                        + "\n";
            } else if (entry.getValue().getStatus().equalsIgnoreCase(ADDED)) {
                result = result + "Property '"
                        + entry.getKey()
                        + "' was added with value: "
                        + formatValue(entry.getValue().getNewValue())
                        + "\n";
            }
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }


    private static String formatValue(Object value) {
        String result;
        if (!String.valueOf(value).equals("null")
                && (value.getClass().getSimpleName().equals("ArrayList")
                || value.getClass().getSimpleName().equals("LinkedHashMap"))) {
            result = "[complex value]";
        } else {
            if (!String.valueOf(value).equals("null")
                    && value.getClass().getSimpleName().equals("String")) {
                result = "'" + String.valueOf(value) + "'";
            } else {
                result = String.valueOf(value);
            }
        }
        return result;
    }
}
