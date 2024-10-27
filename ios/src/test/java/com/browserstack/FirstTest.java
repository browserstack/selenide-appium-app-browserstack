package com.browserstack;

import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.WebDriverRunner;

import io.appium.java_client.AppiumBy;

import java.time.Duration;

public class FirstTest extends AppiumTest {

  @Test
  public void test() throws Exception {
    WebDriverRunner.setWebDriver(driver);

    $(AppiumBy.accessibilityId("Text Button")).shouldBe(visible, Duration.ofSeconds(30)).click();

    $(AppiumBy.accessibilityId("Text Input"))
            .shouldBe(visible, Duration.ofSeconds(30))
            .sendKeys("hello@browserstack.com\n");  // Appending "\n" to simulate Enter key if needed

    sleep(5000);

    // Verify the text output
    String textOutput = $(AppiumBy.accessibilityId("Text Output"))
            .shouldBe(visible, Duration.ofSeconds(30))
            .getText();

    Assert.assertEquals("hello@browserstack.com", textOutput);
  }
}
