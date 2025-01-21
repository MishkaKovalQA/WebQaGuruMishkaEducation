package qa.owner.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/tmp/secret.properties",  // Make sure this comes first
        "classpath:auth.properties"     // Fall back to classpath if secret.properties is not found
})
public interface AuthConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();
}
