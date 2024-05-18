package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String generateResult(List<Map<String, Object>> diffs) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> diff : diffs) {
            String status = String.valueOf(diff.get("status"));
            switch (status) {
                case "ADDED"   -> result.append(String.format("Property '%s' was added with value: %s\n",
                        diff.get("key"), complexValue(diff.get("value"))));
                case "REMOVED" -> result.append(String.format("Property '%s' was removed\n",
                        diff.get("key")));
                case "EQUAL"   -> {}
                case "CHANGED" -> result.append(String.format("Property '%s' was updated. From %s to %s\n",
                        diff.get("key"), complexValue(diff.get("value1")), complexValue(diff.get("value2"))));
                default        -> throw new RuntimeException("Unknown status");
            }
        }

        return result.toString().trim();
    }

    public static String complexValue(Object value) {
        if (value instanceof Object[] || value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}
