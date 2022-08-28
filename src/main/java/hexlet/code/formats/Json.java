package hexlet.code.formats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Changes;

import java.util.Map;


public class Json {

    public static String formatToJson(Map<String, Changes> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(map);
        return result;
    }

}
