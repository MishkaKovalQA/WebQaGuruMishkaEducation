package qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa.demoqa.page.RegistrationPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;

public abstract class BaseTest {

    protected RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setUp() {
        Configuration.browserCapabilities.setCapability("pageLoadStrategy", "eager");
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        //white only that for running in selenoid without browser gui
        Configuration.remote = "http://user1:1234@selenoid.autotests.cloud:4444/wd/hub";

        Configuration.browser = "chrome";
        Configuration.browserVersion = "100.0";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true, //window in window for selenoid
                "enableVideo", true //capture the video, headless should be false
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
