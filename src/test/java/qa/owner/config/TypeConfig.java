package qa.owner.config;

import domain.Browser;
import org.aeonbits.owner.Config;

// check Path, byte[]
public interface TypeConfig extends Config {

    @Key("integer")
    Integer getInteger();

    @Key("double")
    Double getDouble();

    @Key("boolean")
    Boolean getBoolean();

    @Key("enum")
    Browser getEnum();


}
