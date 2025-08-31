package com.mytham.essentials.steps;

import com.mytham.essentials.support.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

public class DynamicSteps {
  private WebDriver driver;
  private WebDriverWait wait;

  @Given("I open the dynamic page")
  public void openDynamic() {
    driver = DriverManager.get();
    wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
  }

  @When("I add a box and reveal the input")
  public void addAndReveal() {
    driver.findElement(By.id("adder")).click();
    assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box0"))));
    driver.findElement(By.id("reveal")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("revealed")));
  }

  @Then("I can type into the revealed input")
  public void typeInto() {
    WebElement revealed = driver.findElement(By.id("revealed"));
    revealed.sendKeys("Displayed");
    assertEquals("Displayed", revealed.getDomProperty("value"));
  }
}