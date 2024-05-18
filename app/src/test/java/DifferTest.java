import hexlet.code.Differ;
import hexlet.code.Utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DifferTest {
    private static String resultStylish1_2;
    private static String resultStylish3_4;
    private static String resultPlain3_4;
    private static String resultJson3_4;

    private static final String pathResultStylish1_2 = "src/test/resources/TestStylish1_2";
    private static final String pathResultStylish3_4 = "src/test/resources/TestStylish3_4";
    private static final String pathResultPlain3_4   = "src/test/resources/TestPlain3_4";
    private static final String pathResultJson3_4    = "src/test/resources/TestJson3_4";

    private static final String pathFile1_json = "src/test/resources/file1.json";
    private static final String pathFile2_json = "src/test/resources/file2.json";
    private static final String pathFile3_json = "src/test/resources/file3.json";
    private static final String pathFile4_json = "src/test/resources/file4.json";

    private static final String pathFile1_yml  = "src/test/resources/file1.yml";
    private static final String pathFile2_yml  = "src/test/resources/file2.yml";
    private static final String pathFile3_yml  = "src/test/resources/file3.yml";
    private static final String pathFile4_yml  = "src/test/resources/file4.yml";



    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish1_2 = Utils.getDataFromFilePath(pathResultStylish1_2);
        resultStylish3_4 = Utils.getDataFromFilePath(pathResultStylish3_4);
        resultPlain3_4   = Utils.getDataFromFilePath(pathResultPlain3_4);
        resultJson3_4    = Utils.getDataFromFilePath(pathResultJson3_4);
    }

    @Test
    public void generateTestStylish() throws Exception {
        assertThat(Differ.generate(pathFile1_json, pathFile2_json)).isEqualTo(resultStylish1_2);
        assertThat(Differ.generate(pathFile3_json, pathFile4_json)).isEqualTo(resultStylish3_4);

        assertThat(Differ.generate(pathFile1_yml,  pathFile2_yml)).isEqualTo(resultStylish1_2);
        assertThat(Differ.generate(pathFile3_yml,  pathFile4_yml)).isEqualTo(resultStylish3_4);
    }

    @Test
    public void generateTestPlain() throws Exception {
        assertThat(Differ.generate(pathFile3_json, pathFile4_json, "plain")).isEqualTo(resultPlain3_4);

        assertThat(Differ.generate(pathFile3_yml,  pathFile4_yml,  "plain")).isEqualTo(resultPlain3_4);
    }

    @Test
    public void generateTestJson() throws Exception {
        assertThat(Differ.generate(pathFile3_json, pathFile4_json, "json")).isEqualTo(resultJson3_4);

        assertThat(Differ.generate(pathFile3_yml,  pathFile4_yml,  "json")).isEqualTo(resultJson3_4);
    }
}
