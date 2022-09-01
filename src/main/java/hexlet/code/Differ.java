package hexlet.code;


import hexlet.code.formats.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Constants.Formats.JSON;
import static hexlet.code.Constants.Formats.YML;
import static hexlet.code.Constants.Formats.STYLISH;


public class Differ {


    public static String generate(String filepath1, String filepath2, String format)
            throws Exception {

        String firstFileFormat = getDataFormat(filepath1);
        String secondFileFormat = getDataFormat(filepath2);

        String firstFileContents = readFile(filepath1, firstFileFormat);
        String secondFileContents = readFile(filepath2, secondFileFormat);

        Map<String, Object> firstMap = Parser.getFileData(firstFileContents, firstFileFormat);
        Map<String, Object> secondMap = Parser.getFileData(secondFileContents, secondFileFormat);

        Map<String, Change> diffs = MapComparator.compareMaps(firstMap, secondMap);

        return Formatter.format(diffs, format);
    }


    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, STYLISH);
    }


    private static String readFile(String filepath, String fileFormat) throws Exception {
        if (!fileFormat.equals(JSON)
                && !(fileFormat.equals(YML))) {
            throw new Exception("File format is incorrect");
        }
        // Creating a path choosing file from local
        // directory by creating an object of Path class
        Path absolutePath = Paths.get(filepath);

        // Now calling Files.readString() method to
        // read the file
        return Files.readString(absolutePath);
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }

}
