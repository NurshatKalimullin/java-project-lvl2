package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapComparator {


    public static LinkedHashMap<String, Object> compareMaps(Map<String, Object> firstMap,
                                                            Map<String, Object> secondMap) {

        LinkedHashMap<String, Object> diffs
                = new LinkedHashMap<>();

        //extract keys and sort them
        Set<String> firstKeys = firstMap.keySet();
        Set<String> secondKeys = secondMap.keySet();
        TreeSet<String> unionKeys = new TreeSet<>(firstKeys);
        unionKeys.addAll(secondKeys);

        for (String key : unionKeys) {
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
                    diffs.put("  " + key, firstMap.get(key));
                } else {
                    diffs.put("- " + key, firstMap.get(key));
                    diffs.put("+ " + key, secondMap.get(key));
                }
            } else if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                diffs.put("- " + key, firstMap.get(key));
            } else {
                diffs.put("+ " + key, secondMap.get(key));
            }
        }

        return diffs;
    }
}
