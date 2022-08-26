package hexlet.code;


import hexlet.code.formats.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class Differ {


    public static String generate(String filepath1, String filepath2, String format)
            throws Exception {

        String firstFileFormat = getDataFormat(filepath1);
        String secondFileFormat = getDataFormat(filepath2);

        String firstFileContents = readFile(filepath1, firstFileFormat);
        String secondFileContents = readFile(filepath2, secondFileFormat);

        Map<String, Object> firstMap = Parser.getFileData(firstFileContents, firstFileFormat, filepath1);
        Map<String, Object> secondMap = Parser.getFileData(secondFileContents, secondFileFormat, filepath2);

        LinkedHashMap<String, Object> diffs = MapComparator.compareMaps(firstMap, secondMap);
        return Formatter.format(diffs, format);
    }


    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }


    private static String readFile(String filepath, String fileFormat) throws Exception {
        if (!fileFormat.equals("json")
                && !(fileFormat.equals("yml"))) {
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

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }

}
