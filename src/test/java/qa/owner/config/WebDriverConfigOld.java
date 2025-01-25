package qa.owner.config;

import constants.Browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class WebDriverConfigOld {

    public String getBaseUrl() {
        //read value from properties
        String baseUrl = System.getProperty("baseUrl");
        //verify default value
        if (Objects.isNull(baseUrl)) {
            baseUrl = "https://github.com";
        }
        //return result
        return baseUrl;
    }

    public Browser getBrowser() {
        String browser = System.getProperty("browser", "CHROME");
        return Browser.valueOf(browser);
    }

    public URL getRemoteUrl() {
        String remoteUrl = System.getProperty("remoteUrl");
        if (Objects.isNull(remoteUrl)) {
            remoteUrl = "http://localhost:4444";
        }
        try {
            return new URL(remoteUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
