package com.test.core.utils.extension;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.test.ui.utils.AllureUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class CustomWatcher implements TestWatcher, AfterTestExecutionCallback,
        BeforeTestExecutionCallback,
        AfterAllCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        SelenideLogger.removeListener("allureListener");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        SelenideLogger.addListener("allureListener", new AllureSelenide().savePageSource(true).screenshots(true));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
        AllureUtils.attachVideoLink(sessionId, context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
        AllureUtils.attachVideoLink(sessionId, context.getDisplayName());
    }


    @Override
    public void afterAll(ExtensionContext context) {
        closeWebDriver();
    }
}
