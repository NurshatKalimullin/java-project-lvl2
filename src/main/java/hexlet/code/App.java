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

    @CommandLine.Parameters(index = "0", description = "path to first file", defaultValue = "project2files/file1.json", paramLabel = "filepath1")
    private File filepath1 = new File("project2files/file1.json");

    @CommandLine.Parameters(index = "1", description = "path to second file", defaultValue = "project2files/file2.json", paramLabel = "filepath2")
    private File filepath2 = new File("project2files/file1.json");

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String format = "stylish";


    @Override
    public String call() throws Exception {
        byte[] firstFileContents = Files.readAllBytes(filepath1.toPath());
        byte[] secondFileContents = Files.readAllBytes(filepath2.toPath());
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        if (format.equals("JSON")) {
            System.out.println("It is JSON");
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> firstMap
                    = mapper.readValue(firstFileContents, new TypeReference<Map<String,Object>>(){});
//            for (String keys : firstMap.keySet())
//            {
//                System.out.println(keys);
//            }

            Map<String, Object> secondMap
                    = mapper.readValue(secondFileContents, new TypeReference<Map<String,Object>>(){});
//            for (String keys : secondMap.keySet())
//            {
//                System.out.println(keys);
//            }


            Map<String, Object> difference
                    = new HashMap<>();


            for (Map.Entry<String, Object> one :
                    firstMap.entrySet()) {
                if (!secondMap.containsKey(one.getKey())) {
                    difference.put("- " + one.getKey(), one.getValue());
                } else if (!secondMap.get(one.getKey()).equals(one.getValue())) {
                    difference.put("- " + one.getKey(), one.getValue());
                } else {
                    difference.put("  " + one.getKey(), one.getValue());
                }
            }
            for (Map.Entry<String, Object> two :
                    secondMap.entrySet()) {
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
                for (Map.Entry<String, Object> o :
                        difference.entrySet()) {
                    if (e.equals(o.getKey())) {
                        last.put(e, o.getValue());
                    }
                }
            }

            for (String keys : last.keySet())
            {
                System.out.println(keys);
            }

        }
        return "";
    }


    public static void main(String [] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
        System.out.println("Hello World!");
    }

}

