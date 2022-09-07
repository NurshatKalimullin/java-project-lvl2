package hexlet.code.formats;

import hexlet.code.Change;

import java.util.Map;

import static hexlet.code.Constants.Statuses.CHANGED;
import static hexlet.code.Constants.Statuses.UNCHANGED;
import static hexlet.code.Constants.Statuses.DELETED;
import static hexlet.code.Constants.Statuses.ADDED;

public class Stylish {

    public static String formatToStylish(Map<String, Change> map) throws Exception {
        StringBuilder mapAsString = new StringBuilder("{\n");
        for (Map.Entry<String, Change> entry : map.entrySet()) {
            String status = entry.getValue().getStatus();
            switch (status) {
                case CHANGED:
                    mapAsString.append("  - ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getOldValue()).append("\n");
                    mapAsString.append("  + ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getNewValue()).append("\n");
                    break;
                case UNCHANGED:
                    mapAsString.append("    ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getOldValue()).append("\n");
                    break;
                case DELETED:
                    mapAsString.append("  - ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getOldValue()).append("\n");
                    break;
                case ADDED:
                    mapAsString.append("  + ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getNewValue()).append("\n");
                    break;
                default:
                    throw new Exception("Wrong status:" + status);
            }
        }

        mapAsString.append("}");
        mapAsString.delete(mapAsString.length() - 1, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
