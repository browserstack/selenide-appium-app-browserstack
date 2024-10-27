package com.browserstack;

import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.WebDriverRunner;
import java.io.File;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumBy;

import java.time.Duration;


public class LocalTest extends AppiumTest {

  @Test
  public void test() throws Exception {
    WebDriverRunner.setWebDriver(driver);

    // Click on the test button
    $(AppiumBy.accessibilityId("TestBrowserStackLocal")).shouldBe(visible, Duration.ofSeconds(30)).click();

    // Wait until the result element has a non-empty value
    String resultString = $(AppiumBy.accessibilityId("ResultBrowserStackLocal"))
            .shouldBe(visible, Duration.ofSeconds(30))
            .shouldNotHave(attribute("value", ""))
            .getText()
            .toLowerCase();

    System.out.println(resultString);

    // If the result contains "not working", take a screenshot and throw an error
    if (resultString.contains("not working")) {
      File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/screenshot.png"));
      System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "/screenshot.png");
      throw new Error("Unexpected BrowserStackLocal test result");
    }

    // Check if the result contains the expected string
    String expectedString = "Up and running";
    Assert.assertTrue(resultString.contains(expectedString.toLowerCase()));
  }
}
