package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTests {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;


    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("src/test/resources/jsonResult.txt");
        resultPlain = readFixture("src/test/resources/plainResult.txt");
        resultStylish = readFixture("src/test/resources/stylishResult.txt");
    }

    private static String readFixture(String path) throws IOException {
        Path resultPath = Path.of(path);
        return Files.readString(resultPath);
    }


    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testJsonFiles(String format) throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        assertThat(Differ.generate(firstFile, secondFile)) .isEqualTo(resultStylish);
        assertThat(Differ.generate(firstFile, secondFile, "stylish")).isEqualTo(resultStylish);
        assertThat(Differ.generate(firstFile, secondFile, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(firstFile, secondFile, "json")).isEqualTo(resultJson);
    }


    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testYmlFiles(String format) throws Exception {
        String firstFile = "src/test/resources/file1.yml";
        String secondFile = "src/test/resources/file2.yml";
        assertThat(Differ.generate(firstFile, secondFile)) .isEqualTo(resultStylish);
        assertThat(Differ.generate(firstFile, secondFile, "stylish")).isEqualTo(resultStylish);
        assertThat(Differ.generate(firstFile, secondFile, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(firstFile, secondFile, "json")).isEqualTo(resultJson);
    }


    @Test
    public void testUnexpectedFile() {
        Exception exception = new Exception("File format is incorrect");
        String expected = exception.getMessage();
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.doc";
        try {
            String diff = Differ.generate(firstFile, secondFile);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(expected);
        }
    }


    @Test
    public void testIncorrectResultFormat() throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        String expected = "txt format is not correct. Please, request stylish or plain or json result formats.";
        try {
            Differ.generate(firstFile, secondFile, "txt");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(expected);
        }

    }
}
