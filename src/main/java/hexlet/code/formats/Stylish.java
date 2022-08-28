package hexlet.code.formats;

import hexlet.code.Changes;

import java.util.Map;

public class Stylish {

    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";
    private static final String DELETED = "deleted";
    private static final String ADDED = "added";

    public static String formatToStylish(Map<String, Changes> map) {
        StringBuilder mapAsString = new StringBuilder("{\n");
        for (Map.Entry<String, Changes> entry : map.entrySet()) {
            if (entry.getValue().getStatus().equalsIgnoreCase(CHANGED)) {
                mapAsString.append("  - ").append(entry.getKey()).append(": ")
                        .append(entry.getValue().getOldValue()).append("\n");
                mapAsString.append("  + ").append(entry.getKey()).append(": ")
                        .append(entry.getValue().getNewValue()).append("\n");
            } else if (entry.getValue().getStatus().equalsIgnoreCase(UNCHANGED)) {
                mapAsString.append("    ").append(entry.getKey()).append(": ")
                        .append(entry.getValue().getOldValue()).append("\n");
            } else if (entry.getValue().getStatus().equalsIgnoreCase(DELETED)) {
                mapAsString.append("  - ").append(entry.getKey()).append(": ")
                        .append(entry.getValue().getOldValue()).append("\n");
            } else if (entry.getValue().getStatus().equalsIgnoreCase(ADDED)) {
                mapAsString.append("  + ").append(entry.getKey()).append(": ")
                        .append(entry.getValue().getNewValue()).append("\n");
            }
        }

//        StringBuilder mapAsString = new StringBuilder("{\n");
//        for (String key : map.keySet()) {
//            mapAsString.append("  ").append(key).append(": ").append(map.get(key)).append("\n");
//        }
        mapAsString.append("}");
        mapAsString.delete(mapAsString.length() - 1, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
