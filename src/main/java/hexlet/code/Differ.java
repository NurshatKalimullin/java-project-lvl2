package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Differ {


    public static String generate(File filepath1, File filepath2, String format)
            throws Exception {

        byte[] firstFileContents = readFile(filepath1);
        byte[] secondFileContents = readFile(filepath2);

        if (format.equals("JSON") || format.equals("stylish")) {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> firstMap
                    = mapper.readValue(firstFileContents, new TypeReference<Map<String, Object>>() { });

            Map<String, Object> secondMap
                    = mapper.readValue(secondFileContents, new TypeReference<Map<String, Object>>() { });


            Map<String, Object> difference
                    = new HashMap<>();


            for (Map.Entry<String, Object> one : firstMap.entrySet()) {
                if (!secondMap.containsKey(one.getKey())) {
                    difference.put("- " + one.getKey(), one.getValue());
                } else if (!secondMap.get(one.getKey()).equals(one.getValue())) {
                    difference.put("- " + one.getKey(), one.getValue());
                } else {
                    difference.put("  " + one.getKey(), one.getValue());
                }
            }
            for (Map.Entry<String, Object> two : secondMap.entrySet()) {
                if (!firstMap.containsKey(two.getKey())) {
                    difference.put("+ " + two.getKey(), two.getValue());
                } else if (!firstMap.get(two.getKey()).equals(two.getValue())) {
                    difference.put("+ " + two.getKey(), two.getValue());
                } else {
                    difference.put("  " + two.getKey(), two.getValue());
                }
            }

            List<String> newList = new ArrayList<>(difference.keySet());


            List<String> elList = newList
                    .stream()
                    .sorted((o1, o2) ->
                            o1.substring(2).compareToIgnoreCase(o2.substring(2))
                    )
                    .collect(Collectors.toList());

            LinkedHashMap<String, Object> last
                    = new LinkedHashMap<>();

            for (String e : elList) {
                for (Map.Entry<String, Object> o : difference.entrySet()) {
                    if (e.equals(o.getKey())) {
                        last.put(e, o.getValue());
                    }
                }
            }

            System.out.println(convertWithIteration(last));
            return convertWithIteration(last);
        }

        return "string";
    }

    public static String generate(File filepath1, File filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String convertWithIteration(Map<String, ?> map) {
        StringBuilder mapAsString = new StringBuilder("{\n");
        for (String key : map.keySet()) {
            mapAsString.append(key + ": " + map.get(key) + "\n");
        }
        mapAsString.append("}");
        mapAsString.delete(mapAsString.length() - 1, mapAsString.length()).append("}");
        return mapAsString.toString();
    }

    private static byte[] readFile(File filepath) throws Exception {
        if (!(filepath.getAbsolutePath()).endsWith(".json")) {
            throw new Exception("File format is incorrect");
        }
        return Files.readAllBytes(filepath.toPath());
    }
}
