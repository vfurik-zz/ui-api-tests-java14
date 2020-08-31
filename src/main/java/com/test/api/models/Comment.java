package com.test.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("preview")
public record Comment(@JsonProperty("postId") int postId,
                      @JsonProperty("id") int id,
                      @JsonProperty("name") String name,
                      @JsonProperty("email") String email,
                      @JsonProperty("body") String body) {
}
