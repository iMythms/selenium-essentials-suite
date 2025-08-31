package com.mytham.essentials.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.net.URL;

public class WebFormPage {
  private final WebDriver driver;
  private final WebDriverWait wait;

  private final By text = By.name("my-text");
  private final By password = By.name("my-password");
  private final By textarea = By.cssSelector("textarea[name='my-textarea']");
  private final By select = By.name("my-select");
  private final By checkbox = By.cssSelector("input[type='checkbox'][name='my-check']");
  private final By radio1 = By.id("my-radio-1");
  private final By radio2 = By.id("my-radio-2");
  private final By color = By.name("my-colors");
  private final By date = By.name("my-date");
  private final By submit = By.cssSelector("button[type='submit']");
  private final By message = By.id("message");

  public WebFormPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  public WebFormPage open() {
    // Prefer local test fixture when present in test resources (helps CI / offline
    // runs)
    URL local = getClass().getClassLoader().getResource("web/web-form.html");
    if (local != null) {
      driver.get(local.toString());
    } else {
      driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }
    return this;
  }

  public WebFormPage setText(String s) {
    WebElement e = wait.until(ExpectedConditions.elementToBeClickable(text));
    e.clear();
    e.sendKeys(s);
    return this;
  }

  public WebFormPage setPassword(String s) {
    WebElement e = driver.findElement(password);
    e.clear();
    e.sendKeys(s);
    return this;
  }

  public WebFormPage setTextarea(String s) {
    WebElement e = driver.findElement(textarea);
    e.clear();
    e.sendKeys(s);
    return this;
  }

  public WebFormPage selectOptionByText(String visible) {
    WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(select));
    new Select(e).selectByVisibleText(visible);
    return this;
  }

  private void safeClick(WebElement e) {
    try {
      // try a normal click first (will wait until element is clickable by caller)
      e.click();
      return;
    } catch (Exception ignored) {
    }
    // fallback: scroll into view and attempt Actions click, then JS click as last
    // resort
    try {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", e);
      new Actions(driver).moveToElement(e).pause(Duration.ofMillis(200)).click().perform();
    } catch (Exception ignored) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
    }
  }

  public WebFormPage setCheckbox(boolean on) {
    WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(checkbox));
    if (e.isSelected() != on) {
      safeClick(e);
    }
    return this;
  }

  public WebFormPage selectRadio(int index) {
    By selector = index == 1 ? radio1 : radio2;
    WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(selector));
    safeClick(e);
    return this;
  }

  public WebFormPage setColor(String hex) {
    WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(color));
    try {
      e.clear();
    } catch (Exception ignored) {
    }
    e.sendKeys(hex);
    return this;
  }

  public WebFormPage setDate(String yyyyMMdd) {
    WebElement e = wait.until(ExpectedConditions.elementToBeClickable(date));
    e.clear();
    e.sendKeys(yyyyMMdd);
    return this;
  }

  public WebFormPage submit() {
    WebElement e = wait.until(ExpectedConditions.elementToBeClickable(submit));
    safeClick(e);
    return this;
  }

  public String getMessage() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText().trim();
  }

  public String selectedOptionValue() {
    WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(select));
    return new Select(e).getFirstSelectedOption().getAttribute("value");
  }
}
