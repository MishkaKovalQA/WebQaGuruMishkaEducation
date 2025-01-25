package qa.owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import qa.owner.config.FruitConfig;

import static constants.Browser.CHROME;
import static constants.Browser.FIREFOX;
import static org.assertj.core.api.Assertions.assertThat;

public class FruitTests {

    @Test
    void arrayTest() {
        System.setProperty("array", "banana,apple");

        FruitConfig config = ConfigFactory.create(FruitConfig.class, System.getProperties());
        assertThat(config.getFruitsArray()).containsExactly("banana", "apple");
    }

    @Test
    void arrayWithDefaultValuesTest() {
        FruitConfig config = ConfigFactory.create(FruitConfig.class, System.getProperties());
        assertThat(config.getFruitsArrayWithDefaultValues()).containsExactly("orange", "apple");
    }

    @Test
    void arrayWithSeparatorTest() {
        System.setProperty("array", "banana;apple");

        FruitConfig config = ConfigFactory.create(FruitConfig.class, System.getProperties());
        assertThat(config.getFruitsArrayWithSeparator()).containsExactly("banana", "apple");
    }

    @Test
    void listTest() {
        System.setProperty("list", "apple,orange");

        FruitConfig config = ConfigFactory.create(FruitConfig.class, System.getProperties());
        assertThat(config.getFruitsList()).containsExactly("apple", "orange");
    }

    @Test
    void browserListTest() {
        System.setProperty("list", "CHROME,FIREFOX");

        FruitConfig config = ConfigFactory.create(FruitConfig.class, System.getProperties());
        assertThat(config.getBrowserList()).containsExactly(CHROME, FIREFOX);
    }
}
