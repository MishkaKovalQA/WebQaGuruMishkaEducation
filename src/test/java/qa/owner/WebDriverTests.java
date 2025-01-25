package qa.owner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import qa.owner.config.WebDriverProvider;

import static constants.Tags.WEB_DRIVER_TESTS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag(WEB_DRIVER_TESTS)
public class WebDriverTests {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new WebDriverProvider().get();
    }

    @Test
    public void testGithub() {
        String title = driver.getTitle();
        assertEquals("GitHub · Build and ship software on a single, collaborative platform · GitHub", title);
    }

    @Test
    public void testGithub2() {
        String title = driver.getTitle();
        assertEquals("GitHub · Build and ship software on a single, collaborative platform · GitHub", title);
    }

    //./gradlew clean test -DbaseUrl=https://github.com -Dbrowser=CHROME -Denv=local -PincludeTags=web-driver-tests
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
