package hexlet.code;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {


    public static String generate(String filepath1, String filepath2, String format)
            throws Exception {

        String firstFileContents = readFile(filepath1);
        String secondFileContents = readFile(filepath2);

        LinkedHashMap<String, Object> diffs
                = new LinkedHashMap<>();

        Map<String, Object> firstMap = Parser.getFileData(firstFileContents, filepath1);
        Map<String, Object> secondMap = Parser.getFileData(secondFileContents, filepath2);

        //extract keys and sort them
        Set<String> firstKeys = firstMap.keySet();
        Set<String> secondKeys = secondMap.keySet();
        TreeSet<String> unionKeys = new TreeSet<>(firstKeys);
        unionKeys.addAll(secondKeys);

        for (String key : unionKeys) {
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
                    diffs.put("  " + key, String.valueOf(firstMap.get(key)));
                } else {
                    diffs.put("- " + key, String.valueOf(firstMap.get(key)));
                    diffs.put("+ " + key, String.valueOf(secondMap.get(key)));
                }
            } else if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                diffs.put("- " + key, String.valueOf(firstMap.get(key)));
            } else {
                diffs.put("+ " + key, String.valueOf(secondMap.get(key)));
            }
        }
        return Formatter.format(diffs, format);
    }


    public static String generate(String filepath1, String filepath2) throws Exception {
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

    private static String readFile(String filepath) throws Exception {
        if (!(filepath).endsWith(".json")
                && !(filepath.endsWith(".yaml"))) {
            throw new Exception("File format is incorrect");
        }
        // Creating a path choosing file from local
        // directory by creating an object of Path class
        Path absolutePath
                = Paths.get(filepath);

        String fileContent;
        // Now calling Files.readString() method to
        // read the file
        fileContent = Files.readString(absolutePath);
        return fileContent;
    }
}
