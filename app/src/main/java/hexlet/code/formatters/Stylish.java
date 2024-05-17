package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String generateResult(List<Map<String, Object>> diffs) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> diff : diffs) {
            String status = String.valueOf(diff.get("status"));
            switch (status) {
                case "ADDED"   -> result.append(String.format("  + %s: %s\n",
                        diff.get("key"), diff.get("value")));
                case "REMOVED" -> result.append(String.format("  - %s: %s\n",
                        diff.get("key"), diff.get("value")));
                case "EQUAL"   -> result.append(String.format("    %s: %s\n",
                        diff.get("key"), diff.get("value")));
                case "CHANGED" -> result.append(String.format("  - %s: %s\n  + %s: %s\n",
                        diff.get("key"), diff.get("value1"), diff.get("key"), diff.get("value2")));
                default        -> throw new RuntimeException("Unknown status");
            }
        }
        result.append("}");

        return result.toString();
    }
}
