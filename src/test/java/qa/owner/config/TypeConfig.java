package qa.owner.config;

import domain.Browser;
import org.aeonbits.owner.Config;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

public interface TypeConfig extends Config {

    @Key("integer")
    Integer getInteger();

    @Key("double")
    Double getDouble();

    @Key("boolean")
    Boolean getBoolean();

    @Key("enum")
    Browser getEnum();

    @Key("file")
    File getFile();

    @Key("url")
    URL getUrl();

    @Key("path")
    @ConverterClass(PathConverter.class)
    Path getPath();

    @Key("byteArray")
    byte[] getByteArray();
}
