package com.test.ui.design.components;

import com.test.ui.config.RunConfig;

public enum DesignComponent {

    HOME_PAGE(String.format("expected_screenshot/%s/HomePage.png", RunConfig.config.configName()));

    private String path;

    DesignComponent(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}