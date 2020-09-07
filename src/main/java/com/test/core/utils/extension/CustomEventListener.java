package com.test.core.utils.extension;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class CustomEventListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        super.beforeChangeValueOf(element, driver, keysToSend);
        highlight(element);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
        if (element != null) {
            highlight(element);
        }
    }

    private void highlight(WebElement element) {
        if (element != null) {
            Selenide.executeAsyncJavaScript("""
                            var callback = arguments[arguments.length-1];
                            var elem = arguments[0];
                            elem.style.outline = 'solid red 1px';
                            window.setTimeout(function(){
                                elem.style.outline = 'none';
                            callback()}, 50);
                    """, element);
        }
    }
}