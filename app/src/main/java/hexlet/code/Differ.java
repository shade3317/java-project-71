package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class Differ {
    public enum Status {
        EQUAL,
        ADDED,
        REMOVED,
        CHANGED
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String content1 = getDataFromFilePath(filepath1);
        String content2 = getDataFromFilePath(filepath2);

        Map<String, Object> mapFile1 = parseJson(content1);
        Map<String, Object> mapFile2 = parseJson(content2);

        List<Map<String, Object>> result = generateDiffs(mapFile1, mapFile2);
        return generateResult(result);
    }

    public static String getDataFromFilePath(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File '" + path + "' does not exist");
        }

        return Files.readString(path);
    }

    public static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    public static List<Map<String, Object>> generateDiffs(Map<String, Object> file1, Map<String, Object> file2) {
        var result = new ArrayList<Map<String, Object>>();

        var keys = new TreeSet<String>();
        keys.addAll(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key : keys) {
            var diffElement = generateDiffElement(file1, file2, key);
            result.add(diffElement);
        }

        return result;
    }

    public static Map<String, Object> generateDiffElement(Map<String, Object> file1, Map<String, Object> file2,
                                                          String key) {
        Map<String, Object> diffElement = new LinkedHashMap<>();
        var value1 = file1.get(key);
        var value2 = file2.get(key);

        diffElement.put("key", key);
        if (!file1.containsKey(key)) {
            diffElement.put("status", Status.ADDED);
            diffElement.put("value",  value2);
        } else if (!file2.containsKey(key)) {
            diffElement.put("status", Status.REMOVED);
            diffElement.put("value",  value1);
        } else if (Objects.equals(value1, value2)) {
            diffElement.put("status", Status.EQUAL);
            diffElement.put("value",  value1);
        } else {
            diffElement.put("status", Status.CHANGED);
            diffElement.put("value1", value1);
            diffElement.put("value2", value2);
        }

        return diffElement;
    }

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

