package com.test.api.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommentsClient extends BaseClient {

    @Step
    public Response getComments() {
        return given(this.requestSpecBuilder
                .setBasePath("/comments").build())
                .when()
                .get();
    }

    @Step
    public Response getComments(Map<String, Object> params) {
        return given(this.requestSpecBuilder
                .setBasePath("/comments").build())
                .queryParams(params)
                .when()
                .get();
    }

    @Step
    public Response getComment(int id) {
        return given(this.requestSpecBuilder.setBasePath(String.format("/comments/%s", id)).build())
                .when()
                .get();
    }

}
