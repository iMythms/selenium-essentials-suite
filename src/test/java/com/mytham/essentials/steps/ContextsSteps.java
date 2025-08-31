package com.mytham.essentials.steps;

import com.mytham.essentials.support.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.Set;

public class ContextsSteps {
  private WebDriver driver;

  @Given("I open the windows page")
  public void openWindowsPage() {
    driver = DriverManager.get();
    driver.get("https://the-internet.herokuapp.com/windows");
  }

  @When("I open a new window")
  public void openNewWindow() {
    driver.findElement(By.linkText("Click Here")).click();
  }

  @Then("I can assert the new window title and return")
  public void assertNewWindow() {
    String original = driver.getWindowHandle();
    Set<String> handles = driver.getWindowHandles();
    assertEquals(2, handles.size());
    for (String h : handles)
      if (!h.equals(original)) {
        driver.switchTo().window(h);
        break;
      }
    assertTrue(driver.getTitle().toLowerCase().contains("new window"));
    driver.close();
    driver.switchTo().window(original);
  }

  @Given("I open the iframe page")
  public void openIframePage() {
    driver = DriverManager.get();
    driver.get("https://the-internet.herokuapp.com/iframe");
  }

  @When("I type {string} inside the editor")
  public void typeInsideEditor(String text) {
    new WebDriverWait(driver, Duration.ofSeconds(5))
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[id^='mce_']")));
    WebElement editor = driver.findElement(By.id("tinymce"));
    editor.click();
    editor.sendKeys(Keys.chord(Keys.COMMAND, "a"));
    editor.sendKeys(Keys.DELETE);
    editor.sendKeys(text);
    driver.switchTo().defaultContent();
  }

  @Then("I see the text inside the editor")
  public void seeText() {
    new WebDriverWait(driver, Duration.ofSeconds(5))
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[id^='mce_']")));
    String txt = driver.findElement(By.id("tinymce")).getText();
    driver.switchTo().defaultContent();
    assertFalse(txt.isEmpty());
  }
}