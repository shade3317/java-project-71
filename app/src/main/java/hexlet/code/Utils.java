package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;


public class Utils {
    public static String getDataFromFilePath(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }

    public static String getFileType(String filePath) {
        int index = filePath.lastIndexOf('.');
        if (index > 0) {
            return filePath.substring(index + 1);
        }
        throw new RuntimeException("File type not found");
    }
}

