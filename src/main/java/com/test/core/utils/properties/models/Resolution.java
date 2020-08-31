package com.test.core.utils.properties.models;

public enum Resolution {
    FULL_HD("1920x1080", "FULL HD"),
    HD("1366x768", "HD"),
    IPAD("768x1024", "iPad"),
    MOBILE("360x640", "Nexus 5");

    private String resolution;

    private String name;

    Resolution(String resolution, String name) {
        this.resolution = resolution;
        this.name = name;
    }

    public String getResolution() {
        return resolution;
    }

    public String getName() {
        return name;
    }
}
