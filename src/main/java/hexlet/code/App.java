package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "differ 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file",
            defaultValue = "project2files/file31.json", paramLabel = "filepath1")
    private File filepath1;

    @Parameters(index = "1", description = "path to second file",
            defaultValue = "project2files/file42.json", paramLabel = "filepath2")
    private File filepath2;

    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]", paramLabel = "format")
    private String format;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }


    @Override
    public final Integer call() throws Exception {
        String diff = Differ.generate(filepath1, filepath2, format);
        System.out.println(diff);
        return 0;
    }


}

