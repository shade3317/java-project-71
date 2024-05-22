import hexlet.code.Differ;
import hexlet.code.Utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public final class DifferTest {
    private static final String PATH_RESULT_STYLISH = "src/test/resources/TestStylish3_4";
    private static final String PATH_RESULT_PLAIN   = "src/test/resources/TestPlain3_4";
    private static final String PATH_RESULT_JSON    = "src/test/resources/TestJson3_4";

    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;


    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish = Utils.getDataFromFilePath(PATH_RESULT_STYLISH);
        resultPlain   = Utils.getDataFromFilePath(PATH_RESULT_PLAIN);
        resultJson    = Utils.getDataFromFilePath(PATH_RESULT_JSON);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String fileType) throws Exception {
        var pathFile3 = Utils.getFilePath("file3", fileType);
        var pathFile4 = Utils.getFilePath("file4", fileType);

        assertThat(Differ.generate(pathFile3, pathFile4)).isEqualTo(resultStylish);
        assertThat(Differ.generate(pathFile3, pathFile4, "stylish")).isEqualTo(resultStylish);
        assertThat(Differ.generate(pathFile3, pathFile4, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(pathFile3, pathFile4, "json")).isEqualTo(resultJson);
    }
}
