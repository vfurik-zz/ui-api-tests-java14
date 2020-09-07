package com.test.core.utils.extension;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.test.ui.config.RunConfig;
import com.test.ui.utils.AllureUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

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
        if (RunConfig.config.runInSelenoid()) {
            String sessionId = ((RemoteWebDriver) ((EventFiringWebDriver) WebDriverRunner.getWebDriver()).getWrappedDriver()).getSessionId().toString();
            AllureUtils.attachVideoLink(sessionId, context.getDisplayName());
            AllureUtils.attachBrowserLog(sessionId, context.getDisplayName());
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (RunConfig.config.runInSelenoid()) {
            String sessionId = ((RemoteWebDriver) ((EventFiringWebDriver) WebDriverRunner.getWebDriver()).getWrappedDriver()).getSessionId().toString();
            AllureUtils.attachVideoLink(sessionId, context.getDisplayName());
            AllureUtils.attachBrowserLog(sessionId, context.getDisplayName());
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        closeWebDriver();
    }
}
