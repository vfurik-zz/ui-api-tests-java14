package com.test.ui.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.test.core.utils.extension.CustomWatcher;
import com.test.core.utils.extension.html_report.CustomHtmlReport;
import com.test.ui.page.HomePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.test.core.utils.properties.PropertyController.propController;
import static com.test.ui.config.CapabilitiesFactory.capabilities;
import static com.test.ui.config.RunConfig.config;

public abstract class BaseUiTest {
    static {
        Configuration.browserPosition = "0x0";
        Configuration.baseUrl = propController.getUiUrl();
        Configuration.timeout = 12000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.pageLoadStrategy = "normal";
        Configuration.pollingInterval = 100;
        Configuration.fastSetValue = false;
        Configuration.clickViaJs = false;
        Configuration.browser = config.browserName();
        Configuration.browserSize = config.resolution().getResolution();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserCapabilities
                .merge(capabilities.getCapabilities(config));
        if (config.runInSelenoid()) {
            Configuration.remote = propController.getRemoteSelenoid();
        }
    }

    @Step
    public HomePage openHomePage() {
        Selenide.open("/");
        return new HomePage().isLoaded();
    }

    @RegisterExtension
    static TextReportExtension textReportExtension = new TextReportExtension();

    @RegisterExtension
    static CustomHtmlReport customReport = new CustomHtmlReport();

    @RegisterExtension
    @Order(Integer.MAX_VALUE)
    static CustomWatcher customWatcher = new CustomWatcher();

}
