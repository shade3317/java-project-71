package hexlet.code;

import java.util.*;

public class DiffsGenerate {
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
