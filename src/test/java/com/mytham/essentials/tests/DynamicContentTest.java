package com.mytham.essentials.tests;

import com.mytham.essentials.support.JUnitTestBase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class DynamicContentTest extends JUnitTestBase {
  @Test
  void waitsForelementToAppear_thenInteract() {
    driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    driver.findElement(By.id("adder")).click();
    WebElement box0 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box0")));
    assertTrue(box0.isDisplayed());

    WebElement revealed = driver.findElement(By.id("revealed"));
    driver.findElement(By.id("reveal")).click();
    wait.until(d -> revealed.isDisplayed());
    revealed.sendKeys("Displayed");
    assertEquals("Displayed", revealed.getDomProperty("value"));
  }
}
