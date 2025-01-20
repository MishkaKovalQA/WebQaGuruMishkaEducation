package qa.owner;

import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import qa.owner.config.TypeConfig;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import static domain.Browser.CHROME;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeTests {

    @Test
    void testInteger() {
        System.setProperty("integer", "10");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getInteger()).isEqualTo(10);
    }

    @Test
    void testDouble() {
        System.setProperty("double", "10.10");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getDouble()).isEqualTo(10.10);
    }

    @Test
    void testBoolean() {
        System.setProperty("boolean", "true");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getBoolean()).isEqualTo(true);
    }

    @Test
    void testEnum() {
        System.setProperty("enum", "CHROME");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getEnum()).isEqualTo(CHROME);
    }

    @Test
    void testFile() {
        System.setProperty("file", "/path/to/test/file.txt");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getFile()).isEqualTo(new File("/path/to/test/file.txt"));
    }

    @Test
    @SneakyThrows
    void testUrl() {
        System.setProperty("url", "https://github.com");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getUrl()).isEqualTo(new URL("https://github.com"));
    }

    @Test
    public void testPath() {
        System.setProperty("path", "/path/to/test/file.txt");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getPath()).isEqualTo(Path.of("/path/to/test/file.txt"));
    }

    @Test
    void testByteArray() {
        System.setProperty("byteArray", "1, 2, 3");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getByteArray()).isEqualTo(new byte[]{1, 2, 3});
    }
}
