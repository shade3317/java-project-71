package hexlet.code;

import java.util.List;
import java.util.Map;


public class Differ {
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

        List<Map<String, Object>> diffs = DiffGenerator.generateDiffs(mapContentFile1, mapContentFile2);
        return Formatter.generateResult(diffs, format);
    }
}

