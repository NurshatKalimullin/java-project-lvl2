package hexlet.code;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.MyConstants.Statuses.CHANGED;
import static hexlet.code.MyConstants.Statuses.UNCHANGED;
import static hexlet.code.MyConstants.Statuses.DELETED;
import static hexlet.code.MyConstants.Statuses.ADDED;


public class MapComparator {


    public static Map<String, Changes> compareMaps(Map<String, Object> firstMap,
                                                   Map<String, Object> secondMap) {

        Map<String, Changes> diffs = new TreeMap<>();

        //extract keys and sort them
        Set<String> firstKeys = firstMap.keySet();
        Set<String> secondKeys = secondMap.keySet();
        TreeSet<String> unionKeys = new TreeSet<>(firstKeys);
        unionKeys.addAll(secondKeys);

        for (String key : unionKeys) {
            if (firstMap.containsKey(key) && secondMap.containsKey(key)
                    && String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
                diffs.put(key, new Changes(firstMap.get(key), secondMap.get(key), UNCHANGED));
            } else if (firstMap.containsKey(key) && secondMap.containsKey(key)
                    && !String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
                diffs.put(key, new Changes(firstMap.get(key), secondMap.get(key), CHANGED));
            } else if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                diffs.put(key, new Changes(firstMap.get(key), secondMap.get(key), DELETED));
            } else {
                diffs.put(key, new Changes(firstMap.get(key), secondMap.get(key), ADDED));
            }
        }

        return diffs;
    }
}
