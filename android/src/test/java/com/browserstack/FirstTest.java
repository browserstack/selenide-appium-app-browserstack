package com.browserstack;

import java.time.Duration;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.WebDriverRunner;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;

public class FirstTest extends AppiumTest {

    @Test
    public void test() throws Exception {
        WebDriverRunner.setWebDriver(driver);
        $(AppiumBy.accessibilityId("Search Wikipedia")).shouldBe(visible, Duration.ofSeconds(30)).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).shouldBe(visible, Duration.ofSeconds(30)).sendKeys("BrowserStack");

        sleep(5000); // Selenide's sleep

        ElementsCollection allProductsName  = $$(AppiumBy.className("android.widget.TextView"));
        allProductsName.shouldHave(sizeGreaterThan(0));

    }
}
