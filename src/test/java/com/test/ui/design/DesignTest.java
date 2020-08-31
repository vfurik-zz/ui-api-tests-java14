package com.test.ui.design;

import com.test.ui.config.BaseUiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.test.ui.design.ashot.AshotHelper.compareScreenshot;
import static com.test.ui.design.components.DesignComponent.HOME_PAGE;

@Feature("Design")
public class DesignTest extends BaseUiTest {

    @Story("Design Home page")
    @Tags(value = {@Tag("ui"), @Tag("design")})
    @Test
    void homePage() {
        openHomePage()
                .typeText("q");
        compareScreenshot(HOME_PAGE);
    }

}