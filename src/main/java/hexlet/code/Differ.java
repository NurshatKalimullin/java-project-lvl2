package hexlet.code;


import java.io.File;

public class Differ {


    public static String generate(String filepath1, File filepath2, String format){
        if (format.equals("JSON")) {
            Json.generateJsonDiffer(filepath1, filepath2);
        }

        return "string";
    }
}
