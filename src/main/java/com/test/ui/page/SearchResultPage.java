package com.test.ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage extends BasePage {

    private SelenideElement searchResult = $("div#res");

    @Override
    public SearchResultPage isLoaded() {
        searchResult.shouldBe(Condition.visible);
        return this;
    }
}
