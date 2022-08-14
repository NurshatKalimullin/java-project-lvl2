package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTests {

    @Test
    public void testJsonDiffer() throws Exception {
        String expected = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        File firstFile = new File("src/test/resources/file1.json");
        File secondFile = new File("src/test/resources/file2.json");
        String diff = Differ.generate(firstFile, secondFile, "JSON");
        assertThat(diff).isEqualTo(expected);
    }

    @Test
    public void testUnexpectedFile() {
        Exception exception = new Exception("File format is incorrect");
        String expected = exception.getMessage();
        File firstFile = new File("src/test/resources/file1.json");
        File secondFile = new File("src/test/resources/file2.doc");
        try {
            String diff = Differ.generate(firstFile, secondFile, "JSON");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(expected);
        }
    }
}
