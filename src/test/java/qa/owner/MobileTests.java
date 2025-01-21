package qa.owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import qa.owner.config.MobileConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class MobileTests {

    @Test
    void mobileConfigTest() {
        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(config.getPlatformName()).isEqualTo("IOS");
        assertThat(config.getDeviceName()).isEqualTo("iPhone 13 Pro Max Super Man");
        assertThat(config.getPlatformVersion()).isEqualTo("13");
    }

    @Test
    void mobileConfigWithSystemOverrideTest() {
        System.setProperty("platform.version", "12");

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(config.getPlatformVersion()).isEqualTo("12");
    }

    @Test
    void mobileConfigWithAndroidTest() {
        System.setProperty("device", "google-pixel");

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(config.getPlatformName()).isEqualTo("Android");
        assertThat(config.getDeviceName()).isEqualTo("Google Pixel");
        assertThat(config.getPlatformVersion()).isEqualTo("24");
    }

    @Test
    void mobileConfigWithIphoneTest() {
        System.setProperty("device", "iphone-13");

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(config.getPlatformName()).isEqualTo("IOS");
        assertThat(config.getDeviceName()).isEqualTo("iPhone 13 Pro Max Super Man");
        assertThat(config.getPlatformVersion()).isEqualTo("13");
    }
}
