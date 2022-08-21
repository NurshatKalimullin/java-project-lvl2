package hexlet.code.formats;

import java.util.LinkedHashMap;
import java.util.Map;

public class Plain {

    public static String formatToStylish(LinkedHashMap<String, Object> map) {
        String result = "";

        for (Map.Entry<String, Object> entry1 : map.entrySet()) {
            String key1 = entry1.getKey();

            if (key1.startsWith("- ")) {
                Object value1 = entry1.getValue();

                for (Map.Entry<String, Object> entry2 : map.entrySet()) {
                    String key2 = entry2.getKey();
                    Object value2 = entry2.getValue();

                    if (key1.substring(2).equals(key2.substring(2)) &&
                            key2.startsWith("+ ")) {
                        result = result + "Property '"
                                + key1.substring(2)
                                + "' was updated. From "
                                + formatValue(value1)
                                + " to "
                                + formatValue(value2)
                                + "\n";
                        break;
                    }
                }
                if (!map.containsKey("+ " + key1.substring(2))){
                    result = result + "Property '"
                            + key1.substring(2)
                            + "' was removed"
                            + "\n";
                }
            } else if (key1.startsWith("+ ") && !map.containsKey("- " + key1.substring(2))){
                result = result + "Property '"
                        + key1.substring(2)
                        + "' was added with value: "
                        + formatValue(entry1.getValue())
                        + "\n";
            }
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }


    private static String formatValue(Object value) {
        String result;
        if (!String.valueOf(value).equals("null") &&
                (value.getClass().getSimpleName().equals("ArrayList") ||
                value.getClass().getSimpleName().equals("LinkedHashMap"))) {
            result = "[complex value]";
        } else {
            if (!String.valueOf(value).equals("null") &&
                    value.getClass().getSimpleName().equals("String")) {
                result = "'" + String.valueOf(value) + "'";
            } else {
                result = String.valueOf(value);
            }
        }
        return result;
    }
}
