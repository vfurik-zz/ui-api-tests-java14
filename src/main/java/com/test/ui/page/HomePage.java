package com.test.ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage {

    private SelenideElement page = $("div#main");
    private SelenideElement searchFrom = $("input[type='text'],input[type='search']");

    @Override
    @Step
    public HomePage isLoaded() {
        page.shouldBe(Condition.visible);
        searchFrom.shouldBe(Condition.visible);
        return this;
    }

    @Step("Type text {text}")
    public HomePage typeText(String text) {
        searchFrom.setValue(text);
        return this;
    }

    @Step("Search by {text}")
    public SearchResultPage searchBy(String text) {
        typeText(text);
        searchFrom.pressEnter();
        return new SearchResultPage().isLoaded();
    }

}
