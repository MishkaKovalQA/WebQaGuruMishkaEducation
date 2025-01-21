package qa.owner.config;

import domain.Browser;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("browser.name")
    @DefaultValue("CHROME")
    Browser getBrowserName();

    @Key("browser.version")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("browser.size")
    String getBrowserSize();
}
