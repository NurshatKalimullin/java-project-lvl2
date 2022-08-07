package hexlet.code;

import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "differ 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App {


    @CommandLine.Parameters(index = "0", description = "path to first file", defaultValue = "/etc/hosts", paramLabel = "filepath1")
    private File filepath1 = new File("/etc/hosts");

    @CommandLine.Parameters(index = "1", description = "path to second file", defaultValue = "/etc/hosts", paramLabel = "filepath2")
    private File filepath2 = new File("/etc/hosts");

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String format = "stylish";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
        System.out.println("Hello World!");
    }

}

