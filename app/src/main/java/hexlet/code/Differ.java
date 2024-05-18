package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;


public class Differ {
    public enum Status {
        EQUAL,
        ADDED,
        REMOVED,
        CHANGED
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String contentFile1  = Utils.getDataFromFilePath(filePath1);
        String contentFile2  = Utils.getDataFromFilePath(filePath2);
        String fileType1     = Utils.getFileType(filePath1);
        String fileType2     = Utils.getFileType(filePath2);

        Map<String, Object> mapContentFile1 = Parser.parse(contentFile1, fileType1);
        Map<String, Object> mapContentFile2 = Parser.parse(contentFile2, fileType2);

        List<Map<String, Object>> diffs = generateDiffs(mapContentFile1, mapContentFile2);
        return Formatter.generateResult(diffs, format);
    }

    public static List<Map<String, Object>> generateDiffs(Map<String, Object> mapContentFile1,
                                                          Map<String, Object> mapContentFile2) {
        var diffs = new ArrayList<Map<String, Object>>();

        var keys = new TreeSet<String>();
        keys.addAll(mapContentFile1.keySet());
        keys.addAll(mapContentFile2.keySet());

        for (String key : keys) {
            var diffElement = generateDiffElement(mapContentFile1, mapContentFile2, key);
            diffs.add(diffElement);
        }

        return diffs;
    }

    public static Map<String, Object> generateDiffElement(Map<String, Object> mapContentFile1,
                                                          Map<String, Object> mapContentFile2, String key) {
        Map<String, Object> diffElement = new LinkedHashMap<>();
        var value1 = mapContentFile1.get(key);
        var value2 = mapContentFile2.get(key);

        diffElement.put("key", key);
        if (!mapContentFile1.containsKey(key)) {
            diffElement.put("status", Status.ADDED);
            diffElement.put("value",  value2);
        } else if (!mapContentFile2.containsKey(key)) {
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
}

