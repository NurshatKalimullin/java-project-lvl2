package hexlet.code.formats;

import java.util.LinkedHashMap;

public class Stylish {

    public static String formatToStylish(LinkedHashMap<String, Object> map) {
        StringBuilder mapAsString = new StringBuilder("{\n");
        for (String key : map.keySet()) {
            mapAsString.append("  ").append(key).append(": ").append(map.get(key)).append("\n");
        }
        mapAsString.append("}");
        mapAsString.delete(mapAsString.length() - 1, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
