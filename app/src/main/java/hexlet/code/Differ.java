package hexlet.code;
import hexlet.code.Utils;
import hexlet.code.Parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import java.io.IOException;


public class Differ {
    public enum Status {
        EQUAL,
        ADDED,
        REMOVED,
        CHANGED
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1  = Utils.getDataFromFilePath(filePath1);
        String content2  = Utils.getDataFromFilePath(filePath2);
        String fileType1 = Utils.getFileType(filePath1);
        String fileType2 = Utils.getFileType(filePath2);

        Map<String, Object> mapFile1 = Parser.parse(content1, fileType1);
        Map<String, Object> mapFile2 = Parser.parse(content2, fileType2);

        List<Map<String, Object>> diffs = generateDiffs(mapFile1, mapFile2);
        return generateResult(diffs);
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

