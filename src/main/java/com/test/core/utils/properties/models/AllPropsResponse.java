package com.test.core.utils.properties.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@SuppressWarnings("preview")
public record AllPropsResponse(@JsonProperty("configs") List<Config> configs) {
}
