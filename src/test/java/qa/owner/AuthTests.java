package qa.owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import qa.owner.config.AuthConfig;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTests {

    @Test
    void authClasspathTest() {
        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

        assertThat(config.username()).isEqualTo("m.koval");
        assertThat(config.password()).isEqualTo("123123123");
    }

    //todo fix test
    @Disabled("Doesn't work")
    @Test
    void testAuthWithSecretFile() throws Exception {
        Path secret = Files.createTempFile("secret", ".properties");
        String content = "username=secret-user\npassword=secret-pass";
        Files.write(secret, content.getBytes(StandardCharsets.UTF_8));

        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

        assertThat(config.username()).isEqualTo("secret-user");
        assertThat(config.password()).isEqualTo("secret-pass");
        Files.delete(secret);
    }
}
