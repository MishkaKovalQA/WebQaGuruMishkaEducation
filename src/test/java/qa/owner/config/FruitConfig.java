package qa.owner.config;

import domain.Browser;
import org.aeonbits.owner.Config;

import java.util.List;

public interface FruitConfig extends Config {

    @Key("array")
    String[] getFruitsArray();

    @Key("array")
    @DefaultValue("orange,apple")
    String[] getFruitsArrayWithDefaultValues();

    @Key("list")
    List<String> getFruitsList();

    @Key("array")
    @Separator(";")
    @DefaultValue("orange;banana")
    String[] getFruitsArrayWithSeparator();

    @Key("list")
    List<Browser> getBrowserList();
}
