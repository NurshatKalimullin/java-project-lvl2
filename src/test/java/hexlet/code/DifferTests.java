package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTests {

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


    @Test
    public void testJsonFilesStylishResult() throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        String diff = Differ.generate(firstFile, secondFile, "stylish");
        assertThat(diff).isEqualTo(resultStylish);
    }


    @Test
    public void testJsonFilesPlainResult() throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        String diff = Differ.generate(firstFile, secondFile, "plain");
        assertThat(diff).isEqualTo(resultPlain);
    }


    @Test
    public void testJsonFilesJsonResult() throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        String diff = Differ.generate(firstFile, secondFile, "json");
        assertThat(diff).isEqualTo(resultJson);
    }


    @Test
    public void testJsonFilesDefaultResult() throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        String diff = Differ.generate(firstFile, secondFile);
        assertThat(diff).isEqualTo(resultStylish);
    }


    @Test
    public void testYamlFilesStylishResult() throws Exception {
        String firstFile = "src/test/resources/file1.yml";
        String secondFile = "src/test/resources/file2.yml";
        String diff = Differ.generate(firstFile, secondFile, "stylish");
        assertThat(diff).isEqualTo(resultStylish);
    }


    @Test
    public void testYamlFilesPlainResult() throws Exception {
        String firstFile = "src/test/resources/file1.yml";
        String secondFile = "src/test/resources/file2.yml";
        String diff = Differ.generate(firstFile, secondFile, "plain");
        assertThat(diff).isEqualTo(resultPlain);
    }


    @Test
    public void testYamlFilesDefaultResult() throws Exception {
        String firstFile = "src/test/resources/file1.yml";
        String secondFile = "src/test/resources/file2.yml";
        String diff = Differ.generate(firstFile, secondFile);
        assertThat(diff).isEqualTo(resultStylish);
    }


    @Test
    public void testYamlFilesJsonResult() throws Exception {
        String firstFile = "src/test/resources/file1.yml";
        String secondFile = "src/test/resources/file2.yml";
        String diff = Differ.generate(firstFile, secondFile, "json");
        assertThat(diff).isEqualTo(resultJson);
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
    public void testEmptyJsonFiles() throws Exception {
        String firstFile = "src/test/resources/emptyJson2.json";
        String secondFile = "src/test/resources/emptyJson2.json";
        String diff = Differ.generate(firstFile, secondFile);
        String expected = "{\n}";
        assertThat(diff).isEqualTo(expected);
    }
}
