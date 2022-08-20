package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {


    public static Map<String, Object> getFileData(String fileContents) throws IOException {
        Map<String, Object> map = new HashMap<>();

        if (fileContents.endsWith(".json")) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            map = mapper.readValue(fileContents, new TypeReference<>() {
            });
        }

        if (fileContents.endsWith(".yaml")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            map = mapper.readValue(fileContents, new TypeReference<>() { });
        }

        return map;
    }
}
