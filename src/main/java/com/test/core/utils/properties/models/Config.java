package com.test.core.utils.properties.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("preview")
public record Config(
        @JsonProperty("config_name") ConfigName configName,
        @JsonProperty("resolution") Resolution resolution,
        @JsonProperty("browser_name") String browserName,
        @JsonProperty("browser_version") String browserVersion,
        @JsonProperty("run_in_selenoid") boolean runInSelenoid) {
}
