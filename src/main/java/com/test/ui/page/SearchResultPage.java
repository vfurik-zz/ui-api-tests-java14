package com.test.ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage extends BasePage {

    private SelenideElement searchResult = $("div#res");

    @Override
    public SearchResultPage isLoaded() {
        searchResult.shouldBe(Condition.visible);
        return this;
    }

    @Step
    public SearchResultPage searchResultHasSize(int expectedSize) {
        searchResult.$$("div#res a h3").shouldHaveSize(expectedSize);
        return this;
    }

    @Step
    public SearchResultPage firstResultIs(String expectedResult) {
        searchResult.$$("div#res a h3").first()
                .shouldHave(Condition.text(expectedResult));
        return this;
    }

    @Step
    public SearchResultPage clearSearch() {
        $("[jsname='itVqKe']").click();
        return this;
    }
}
