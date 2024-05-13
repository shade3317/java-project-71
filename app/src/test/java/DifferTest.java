import hexlet.code.Differ;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DifferTest {
    private static String resultStylish;


    @BeforeAll
    public static void beforeAll() throws Exception {
        String pathResultStylish = "src/test/resources/resultStylish";
        resultStylish = Differ.getDataFromFilePath(pathResultStylish);
    }

    @Test
    public void generateTest() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        assertThat(Differ.generate(path1, path2, "stylish")).isEqualTo(resultStylish);
    }
}
