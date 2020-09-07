package com.test.core.utils.properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.core.utils.properties.models.AllPropsResponse;
import com.test.core.utils.properties.models.Config;
import com.test.core.utils.properties.models.ConfigName;
import org.aeonbits.owner.ConfigFactory;

import java.io.IOException;

public class PropertyController {

    private PropertyController() {
        loadPropertiesFromFile();
    }

    final static public PropertyController propController = new PropertyController();

    private PropertyConfig propertyConfig = ConfigFactory.create(PropertyConfig.class);

    private AllPropsResponse allPropsResponse;

    public String getApiUrl() {
        return propertyConfig.apiUrl();
    }

    public String getUiUrl() {
        return propertyConfig.uiUrl();
    }

    public String getRemoteSelenoid() {
        return propertyConfig.selenoidUrl();
    }

    public String getSelenoidVideoPath() {
        return propertyConfig.videoUrl();
    }

    public String getSelenoidLogPath() {
        return propertyConfig.logPath();
    }

    public Config getConfig(ConfigName configName) {
        return this.allPropsResponse.configs()
                .stream()
                .filter(e -> e.configName().equals(configName))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Can not find config by name: %s", configName)));
    }

    private void loadPropertiesFromFile() {
        try {
            this.allPropsResponse = new ObjectMapper().readValue(this.getClass().getClassLoader().getResourceAsStream("allProps.json"), AllPropsResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Error during reading properties from file allProps.json", e);
        }
    }
}