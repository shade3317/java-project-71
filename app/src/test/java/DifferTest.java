import hexlet.code.Differ;
import hexlet.code.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DifferTest {
    private static String resultStylish;


    @BeforeAll
    public static void beforeAll() throws Exception {
        String pathResultStylish = "src/test/resources/TestStylish";
        resultStylish = Utils.getDataFromFilePath(pathResultStylish);
    }

    @Test
    public void generateTest() throws Exception {
        String pathJson1 = "src/test/resources/file1.json";
        String pathJson2 = "src/test/resources/file2.json";
        String pathYml1  = "src/test/resources/file1.yml";
        String pathYml2  = "src/test/resources/file2.yml";
        assertThat(Differ.generate(pathJson1, pathJson2, "stylish")).isEqualTo(resultStylish);
        assertThat(Differ.generate(pathYml1,  pathYml2,  "stylish")).isEqualTo(resultStylish);
    }
}
