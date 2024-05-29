package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;


public class DiffGenerator {
    public static List<Map<String, Object>> generateDiff(Map<String, Object> mapContentFile1,
                                                         Map<String, Object> mapContentFile2) {
        var diff = new ArrayList<Map<String, Object>>();

        var keys = new TreeSet<String>();
        keys.addAll(mapContentFile1.keySet());
        keys.addAll(mapContentFile2.keySet());

        for (String key : keys) {
            var diffElement = generateDiffElement(mapContentFile1, mapContentFile2, key);
            diff.add(diffElement);
        }

        return diff;
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

