package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Json;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String generateResult(List<Map<String, Object>> diffs, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.generateResult(diffs);
            case "plain"   -> Plain.generateResult(diffs);
            case "json"    -> Json.generateResult(diffs);
            default -> throw new RuntimeException("Unknown format");
        };
    }
}
