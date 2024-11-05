package com.browserstack;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LocalTest extends AppiumTest {

    @Test
    public void test() throws Exception {
        WebDriverRunner.setWebDriver(driver);

        // Click on the test action button
        $(AppiumBy.id("com.example.android.basicnetworking:id/test_action")).shouldBe(enabled, Duration.ofSeconds(30)).click();

        // Wait for TextView elements to appear and capture all of them
        ElementsCollection allTextViewElements = $$(AppiumBy.className("android.widget.TextView")).shouldBe(sizeGreaterThan(0), Duration.ofSeconds(30));

        // Find the specific TextView element with the required text
        WebElement testElement = allTextViewElements.stream()
                .filter(textElement -> textElement.getText().contains("The active connection is"))
                .findFirst()
                .orElse(null);

        // Take a screenshot and throw an error if the required TextView element is not found
        if (testElement == null) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/screenshot.png"));
            System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "/screenshot.png");
            throw new Error("Cannot find the needed TextView element from app");
        }

        // Verify the text in the found element
        String matchedString = testElement.getText();
        System.out.println(matchedString);
        Assert.assertTrue(matchedString.contains("The active connection is wifi"));
        Assert.assertTrue(matchedString.contains("Up and running"));
    }
}
