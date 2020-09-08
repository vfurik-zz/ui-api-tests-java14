package com.test.api.clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

import static com.test.core.utils.properties.PropertyController.propController;

public abstract class BaseClient {


    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setBaseUri(propController.getApiUrl())
            .setRelaxedHTTPSValidation()
            .setUrlEncodingEnabled(true)
            .log(LogDetail.ALL)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .addFilters(Arrays.asList(new AllureRestAssured(),
                    new ResponseLoggingFilter(getPrintStream()),
                    new ErrorLoggingFilter()));

    private PrintStream getPrintStream() {
        try {
            return new PrintStream(new File("build/allure-logs.log"));
        } catch (FileNotFoundException e) {
            return System.out;
        }
    }

}
