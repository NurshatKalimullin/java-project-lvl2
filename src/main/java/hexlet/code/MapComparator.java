package hexlet.code;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class MapComparator {


    public static Map<String, Changes> compareMaps(Map<String, Object> firstMap,
                                                   Map<String, Object> secondMap) {

//        LinkedHashMap<String, Object> diffs
//                = new LinkedHashMap<>();
        Map<String, Changes> diffs = new TreeMap<>();

        //extract keys and sort them
        Set<String> firstKeys = firstMap.keySet();
        Set<String> secondKeys = secondMap.keySet();
        TreeSet<String> unionKeys = new TreeSet<>(firstKeys);
        unionKeys.addAll(secondKeys);

//        for (String key : unionKeys) {
//            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
//                if (String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
//                    diffs.put("  " + key, firstMap.get(key));
//                } else {
//                    diffs.put("- " + key, firstMap.get(key));
//                    diffs.put("+ " + key, secondMap.get(key));
//                }
//            } else if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
//                diffs.put("- " + key, firstMap.get(key));
//            } else {
//                diffs.put("+ " + key, secondMap.get(key));
//            }
//        }


        for (String key : unionKeys) {
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
                    diffs.put(key, new Changes(firstMap.get(key), secondMap.get(key), "unchanged"));
                } else {
                    diffs.put(key, new Changes(firstMap.get(key), secondMap.get(key), "changed"));
                }
            } else if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                diffs.put(key, new Changes(firstMap.get(key), secondMap.get(key), "deleted"));
            } else {
                diffs.put(key, new Changes(firstMap.get(key), secondMap.get(key), "added"));
            }
        }

        return diffs;
    }
}
