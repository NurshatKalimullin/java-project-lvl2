package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

import static hexlet.code.Constants.Formats.JSON;
import static hexlet.code.Constants.Formats.YML;

public class Parser {


    public static Map<String, Object> getFileData(String fileContents, String fileFormat)
            throws Exception {
        Map<String, Object> map = new HashMap<>();
        switch (fileFormat) {
            case JSON:
                map = getMapFromJson(fileContents);
                break;
            case YML:
                map = getMapFromYml(fileContents);
                break;
            default:
                throw new Exception("Wrong file format. Please, upload correct file.");
        }
        return map;
    }

    private static Map<String, Object> getMapFromYml(String fileContents) throws JsonProcessingException {
        Map<String, Object> map;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        map = mapper.readValue(fileContents, new TypeReference<>() { });
        return map;
    }

    private static Map<String, Object> getMapFromJson(String fileContents)
            throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        Map<String, Object> map = mapper.readValue(fileContents, new TypeReference<>() { });
        return map;
    }
}
