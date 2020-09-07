package com.test.ui.config;

import com.test.core.utils.properties.models.Config;
import com.test.core.utils.properties.models.ConfigName;
import org.apache.commons.lang3.StringUtils;

import static com.test.core.utils.properties.PropertyController.propController;

public class RunConfig {

    private RunConfig() {

    }

    final static public Config config;

    static {
        String configNameEnv = System.getProperty("config");
        try {
            var configName = StringUtils.isNoneEmpty(configNameEnv) ?
                    ConfigName.valueOf(configNameEnv) : ConfigName.IPAD_RESOLUTION;
            config = propController.getConfig(configName);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Incorrect test config: %s", configNameEnv));
        }
    }
}