package com.test.core.utils.properties;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface PropertyConfig extends Config {
    @Key("api.url")
    String apiUrl();

    @Key("ui.url")
    String uiUrl();

    @Key("selenoid.url")
    String selenoidUrl();

    @Key("video.path")
    String videoUrl();
}
