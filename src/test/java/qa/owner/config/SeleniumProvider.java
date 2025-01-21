package qa.owner.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumProvider {

    public static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static void setConfig() {

        Configuration.baseUrl = SeleniumProvider.config.getBaseUrl();
        Configuration.browser = SeleniumProvider.config.getBrowserName().getName();
        Configuration.browserVersion = SeleniumProvider.config.getBrowserVersion();
        Configuration.browserSize = SeleniumProvider.config.getBrowserSize();
        String remoteUrl = SeleniumProvider.config.getRemoteUrl();
        if (remoteUrl != null) {
            Configuration.remote = remoteUrl;
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browserCapabilities = capabilities;
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
    }
}