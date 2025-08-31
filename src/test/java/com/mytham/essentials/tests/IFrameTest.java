package com.mytham.essentials.tests;

import com.mytham.essentials.support.JUnitTestBase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class IFrameTest extends JUnitTestBase {

  @Test
  void editTinyMCEInsideIframe() {
    driver.get("https://the-internet.herokuapp.com/iframe");

    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[id^='mce_']")));

    WebElement editor = driver.findElement(By.id("tinymce"));
    // Use JS to set the editor content (avoids click interception and platform key
    // differences)
    ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = arguments[1];", editor, "Hello Frame!");
    assertTrue(editor.getText().contains("Hello Frame!"));

    driver.switchTo().defaultContent();
    assertTrue(driver.findElement(By.tagName("h3")).getText().contains("TinyMCE"));
  }
}