package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "differ 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {

    @CommandLine.Parameters(index = "0", description = "path to first file", defaultValue = "project2files/file31.json", paramLabel = "filepath1")
    private File filepath1 = new File("project2files/file13.json");

    @CommandLine.Parameters(index = "1", description = "path to second file", defaultValue = "project2files/file42.json", paramLabel = "filepath2")
    private File filepath2 = new File("project2files/file14.json");

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String format = "stylish";


    @Override
    public String call() throws Exception {
        String diff = Differ.generate(filepath1, filepath2, format);
        System.out.println(diff);
        return "";
    }


    public static void main(String [] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
        System.out.println("Hello World!");
    }

}

