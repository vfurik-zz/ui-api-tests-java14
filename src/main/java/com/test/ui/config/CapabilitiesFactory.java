package com.test.ui.config;

import com.test.core.utils.properties.models.Config;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.HashMap;
import java.util.Map;

public class CapabilitiesFactory {

    private CapabilitiesFactory() {

    }

    public final static CapabilitiesFactory capabilities = new CapabilitiesFactory();

    public DesiredCapabilities getCapabilities(Config config) {
        return switch (config.configName()) {
            case CHROME_HD, CHROME_FULL_HD -> chromeDesiredCapabilities();
            case FIREFOX_FULL_HD -> firefoxDesiredCapabilities();
            case LOCAL -> localChrome();
            case IPAD_RESOLUTION, MOBILE_RESOLUTION -> mobileDesiredCapabilities(config.resolution().getName());
        };
    }

    private DesiredCapabilities chromeDesiredCapabilities() {
        var browserCap = new DesiredCapabilities();
        browserCap.setCapability("enableVNC", true);
        browserCap.setCapability("enableVideo", true);

        var options = new ChromeOptions();
        options.addArguments("disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("disable-extensions");

        browserCap.setCapability(ChromeOptions.CAPABILITY, options);
        return browserCap;
    }

    private DesiredCapabilities firefoxDesiredCapabilities() {
        var browserCap = new DesiredCapabilities();
        browserCap.setCapability("enableVNC", true);
        browserCap.setCapability("enableVideo", true);
        return browserCap;
    }

    private DesiredCapabilities mobileDesiredCapabilities(String deviceName) {
        var browserCap = new DesiredCapabilities();
        browserCap.setCapability("enableVNC", true);
        browserCap.setCapability("enableVideo", true);
        browserCap.setCapability("name", deviceName);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("disable-extensions");

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        browserCap.setCapability(ChromeOptions.CAPABILITY, options);
        return browserCap;
    }

    private DesiredCapabilities localChrome() {
        var browserCap = new DesiredCapabilities();
        var options = new ChromeOptions();
        options.addArguments("disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("disable-extensions");
        browserCap.setCapability(ChromeOptions.CAPABILITY, options);
        return browserCap;
    }
}
