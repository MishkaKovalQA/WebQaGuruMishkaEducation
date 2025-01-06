package qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;

public abstract class BaseTest {

    @BeforeEach
    void setUp() {
        Configuration.browserCapabilities.setCapability("pageLoadStrategy", "eager");
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 10000;
    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
