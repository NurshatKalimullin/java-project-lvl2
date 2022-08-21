package hexlet.code;

import org.junit.jupiter.api.Test;


import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTests {

    @Test
    public void testJsonDiffer() throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        String diff = Differ.generate(firstFile, secondFile);
        Path plainResultPath
                = Path.of("src/test/resources/stylishResult.txt");
        String expected = Files.readString(plainResultPath);
        assertThat(diff).isEqualTo(expected);
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
    public void testYamlDiffer() throws Exception {
        String firstFile = "src/test/resources/file12.yaml";
        String secondFile = "src/test/resources/file22.yaml";
        String diff = Differ.generate(firstFile, secondFile);
        Path plainResultPath
                = Path.of("src/test/resources/stylishResult.txt");
        String expected = Files.readString(plainResultPath);
        assertThat(diff).isEqualTo(expected);
    }

    @Test
    public void testPlainResult() throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        String diff = Differ.generate(firstFile, secondFile, "plain" );
        Path plainResultPath
                = Path.of("src/test/resources/plainResult.txt");
        String expected = Files.readString(plainResultPath);
        assertThat(diff).isEqualTo(expected);
    }

    @Test
    public void testJsonResult() throws Exception {
        String firstFile = "src/test/resources/file1.json";
        String secondFile = "src/test/resources/file2.json";
        String diff = Differ.generate(firstFile, secondFile, "json");
        Path jsonResultPath
                = Path.of("src/test/resources/jsonResult.txt");
        String expected = Files.readString(jsonResultPath);
        assertThat(diff).isEqualTo(expected);
    }
}
