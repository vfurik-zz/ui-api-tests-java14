package com.test.core.utils.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import static com.test.core.utils.properties.PropertyController.propController;

public class ReportEnvGenerating {

    public static void main(String[] args) {
        try (OutputStream output = new FileOutputStream("build/allure-results/environment.properties")) {
            var prop = new Properties();
            prop.setProperty("API URL:", propController.getApiUrl());
            prop.setProperty("UI URL:", propController.getUiUrl());
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}