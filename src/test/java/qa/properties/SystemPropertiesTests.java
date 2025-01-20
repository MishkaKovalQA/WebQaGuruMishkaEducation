package qa.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    void simplePropertyTest() {
        String browserName = System.getProperty("browser");
        System.out.println("Browser: " + browserName);
    }

    @Test
    void simplePropertySetterTest() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser");
        System.out.println("Browser: " + browserName);
    }

    @Test
    void simplePropertyDefaultTest() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println("Browser: " + browserName);
    }

    @Test
    void simplePropertySetterOrDefaultTest() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser", "firefox");
        System.out.println("Browser: " + browserName);
    }

    @Test
    @Tag("one_property")
    void onePropertyTest() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println("Browser: " + browserName);
        // ./gradlew clean onePropertyTest -Dbrowser=safari
    }

    @Test
    @Tag("hello_test")
    void helloTest() {
        System.out.println("Hello, " + System.getProperty("user_name", "unknown student"));
        // ./gradlew clean helloTest -Duser_name="Mishka Koval"
        // ./gradlew clean helloTest "-Duser_name=Mishka Koval"
        // ./gradlew clean helloTest onePropertyTest - bad practice
        // ./gradlew clean twoTest - best practice
        //running from suite (testNG) is bad practice, we should use tags
    }

}
