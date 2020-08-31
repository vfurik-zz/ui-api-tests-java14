package com.test.core.utils.extension.html_report;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CustomHtmlReport implements BeforeEachCallback, AfterEachCallback, AfterAllCallback {

    public CustomHtmlReport() {
        this.htmlReport = new HtmlReport();
        this.onSuccessTest = true;
        this.onFailedTest = true;
    }

    private HtmlReport htmlReport;
    private boolean onFailedTest;
    private boolean onSuccessTest;

    public CustomHtmlReport onFailedTest(boolean onFailedTest) {
        this.onFailedTest = onFailedTest;
        return this;
    }

    public CustomHtmlReport onSuccessTest(boolean onSuccessTest) {
        this.onSuccessTest = onSuccessTest;
        return this;
    }

    @Override
    public void afterAll(ExtensionContext context) {
        this.htmlReport.clean();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        this.htmlReport.finish(context.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        if (onFailedTest || onSuccessTest) {
            this.htmlReport.start();
        }
    }
}
