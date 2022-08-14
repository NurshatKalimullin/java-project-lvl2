package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {


    public static Map<String, Object> getFileData(byte[] fileContents, String format) throws IOException {
        Map<String, Object> map = new HashMap<>();

        if (format.equals("JSON") || format.equals("stylish")) {
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(fileContents, new TypeReference<Map<String, Object>>() {
            });
        }

        if (format.equals("YAML") || format.equals("stylish")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            map = mapper.readValue(fileContents, new TypeReference<Map<String, Object>>() { });
        }

        return map;
    }
}
