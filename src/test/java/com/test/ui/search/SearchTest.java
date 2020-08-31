package com.test.ui.search;

import com.codeborne.selenide.Condition;
import com.test.ui.config.BaseUiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

@Feature("Search")
public class SearchTest extends BaseUiTest {

    @Story("Search selenide in google")
    @Tag("ui")
    @Test
    void searchSelenide() {
        openHomePage()
                .searchBy("Selenide");
    }

    @Story("Test should fail")
    @Tag("ui")
    @Test
    void failedTest() {
        openHomePage()
                .searchBy("Selenide");
        $(".test_class").shouldBe(Condition.visible);
    }

}