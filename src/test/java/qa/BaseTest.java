package qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa.demoqa.helper.Attach;
import qa.demoqa.page.RegistrationPage;

import java.util.Map;

import static com.codeborne.selenide.Browsers.CHROME;

public abstract class BaseTest {

    protected RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setUp() {
        Configuration.browserCapabilities.setCapability("pageLoadStrategy", "eager");
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 10000;
        //write only that for running in selenoid without browser gui
        //bad practice, we need do that from jenkins
        //Configuration.remote = "http://user1:1234@" + System.getProperty("selenoid_url", "selenoid.autotests.cloud") + ":4444/wd/hub";
        Configuration.browser = System.getProperty("browser_name", CHROME);
        Configuration.browserVersion = System.getProperty("browser_version", "100.0");
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");

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
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
/*        clearBrowserCookies();
        clearBrowserLocalStorage();*/
    }
}
