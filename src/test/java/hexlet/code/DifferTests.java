package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTests {

    @Test
    public void testJsonDiffer() throws Exception {
        String expected = "{- follow:false  host:hexlet.io- proxy:123.234.53.22" +
                "- timeout:50+ timeout:20+ verbose:true}";
        File firstFile = new File("project2files/file1.json");
        File secondFile = new File("project2files/file2.json");
        String diff = Differ.generate(firstFile, secondFile, "JSON");
        assertThat(diff).isEqualTo(expected);
    }
}
