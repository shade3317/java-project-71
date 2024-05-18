import hexlet.code.Differ;
import hexlet.code.Utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DifferTest {
    private static String resultStylish12;
    private static String resultStylish34;
    private static String resultPlain34;
    private static String resultJson34;

    private static String pathFile1json;
    private static String pathFile2json;
    private static String pathFile3json;
    private static String pathFile4json;
    private static String pathFile1yml;
    private static String pathFile2yml;
    private static String pathFile3yml;
    private static String pathFile4yml;



    @BeforeAll
    public static void beforeAll() throws Exception {
        pathFile1json = "src/test/resources/file1.json";
        pathFile2json = "src/test/resources/file2.json";
        pathFile3json = "src/test/resources/file3.json";
        pathFile4json = "src/test/resources/file4.json";
        pathFile1yml  = "src/test/resources/file1.yml";
        pathFile2yml  = "src/test/resources/file2.yml";
        pathFile3yml  = "src/test/resources/file3.yml";
        pathFile4yml  = "src/test/resources/file4.yml";

        String pathResultStylish12 = "src/test/resources/TestStylish1_2";
        String pathResultStylish34 = "src/test/resources/TestStylish3_4";
        String pathResultPlain34   = "src/test/resources/TestPlain3_4";
        String pathResultJson34    = "src/test/resources/TestJson3_4";

        resultStylish12 = Utils.getDataFromFilePath(pathResultStylish12);
        resultStylish34 = Utils.getDataFromFilePath(pathResultStylish34);
        resultPlain34   = Utils.getDataFromFilePath(pathResultPlain34);
        resultJson34    = Utils.getDataFromFilePath(pathResultJson34);
    }

    @Test
    public void generateTestStylish() throws Exception {
        assertThat(Differ.generate(pathFile1json, pathFile2json)).isEqualTo(resultStylish12);
        assertThat(Differ.generate(pathFile3json, pathFile4json)).isEqualTo(resultStylish34);

        assertThat(Differ.generate(pathFile1yml,  pathFile2yml)).isEqualTo(resultStylish12);
        assertThat(Differ.generate(pathFile3yml,  pathFile4yml)).isEqualTo(resultStylish34);
    }

    @Test
    public void generateTestPlain() throws Exception {
        assertThat(Differ.generate(pathFile3json, pathFile4json, "plain")).isEqualTo(resultPlain34);

        assertThat(Differ.generate(pathFile3yml,  pathFile4yml,  "plain")).isEqualTo(resultPlain34);
    }

    @Test
    public void generateTestJson() throws Exception {
        assertThat(Differ.generate(pathFile3json, pathFile4json, "json")).isEqualTo(resultJson34);

        assertThat(Differ.generate(pathFile3yml,  pathFile4yml,  "json")).isEqualTo(resultJson34);
    }
}
