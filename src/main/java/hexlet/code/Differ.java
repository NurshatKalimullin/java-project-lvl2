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

        String format1 = getDataFormat(filepath1);
        String format2 = getDataFormat(filepath2);

        String content1 = readFile(filepath1, format1);
        String content2 = readFile(filepath2, format2);

        Map<String, Object> data1 = Parser.getFileData(content1, format1);
        Map<String, Object> data2 = Parser.getFileData(content2, format2);

        Map<String, Change> diff = MapComparator.compareMaps(data1, data2);

        return Formatter.format(diff, format);
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
