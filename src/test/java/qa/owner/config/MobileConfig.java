package qa.owner.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        //order is important
        "classpath:${device}.properties",
        "classpath:mobile.properties"
})
public interface MobileConfig extends Config {

    @Key("platform.name")
    String getPlatformName();

    @Key("device.name")
    String getDeviceName();

    @Key("platform.version")
    String getPlatformVersion();
}
