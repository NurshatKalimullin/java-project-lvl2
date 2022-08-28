package hexlet.code.formats;

import hexlet.code.Changes;

import java.util.Map;

import static hexlet.code.MyConstants.Statuses.CHANGED;
import static hexlet.code.MyConstants.Statuses.UNCHANGED;
import static hexlet.code.MyConstants.Statuses.DELETED;
import static hexlet.code.MyConstants.Statuses.ADDED;

public class Stylish {

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

        mapAsString.append("}");
        mapAsString.delete(mapAsString.length() - 1, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
