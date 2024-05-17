package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class Parser {
    public static Map<String, Object> parse(String content, String fileType) throws Exception {
        switch (fileType) {
            case "yml", "yaml":
                return parseYaml(content);
            case "json":
                return parseJson(content);
            default:
                throw new Exception("Unknown file type");
        }
    }

    public static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    private static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, Map.class);
    }

}

