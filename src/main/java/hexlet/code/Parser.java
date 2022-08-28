package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static hexlet.code.MyConstants.Formats.JSON;
import static hexlet.code.MyConstants.Formats.YML;

public class Parser {


    public static Map<String, Object> getFileData(String fileContents, String fileFormat, String filepath)
            throws IOException {
        Map<String, Object> map = new HashMap<>();
        switch (fileFormat) {
            case JSON:
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
                mapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
                try {
                    map = mapper.readValue(fileContents, new TypeReference<>() {
                    });
                } catch (MismatchedInputException e) {
                    System.out.println(e);
                }
                break;
            case YML:
                mapper = new ObjectMapper(new YAMLFactory());
                map = mapper.readValue(fileContents, new TypeReference<>() { });
                break;
            default:
                System.out.println("Wrong file format. Please, upload correct file.");
        }
        return map;
    }
}
