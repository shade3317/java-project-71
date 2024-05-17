package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String generateResult(List<Map<String, Object>> diffs, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.generateResult(diffs);
            //case "plain"   -> Plain.generateResult(diffs);
            default -> throw new Exception("Unknown format");
        };
    }
}
