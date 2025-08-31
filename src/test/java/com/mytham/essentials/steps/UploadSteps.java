package com.mytham.essentials.steps;

import com.mytham.essentials.support.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.time.Duration;

public class UploadSteps {
  private WebDriver driver;
  private WebDriverWait wait;
  private Path tmp;

  @Given("I open the upload page")
  public void openUpload() {
    driver = DriverManager.get();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.get("https://the-internet.herokuapp.com/upload");
  }

  @When("I upload a small temp file")
  public void upload() throws Exception {
    tmp = Paths.get(System.getProperty("java.io.tmpdir"), "upload-demo.txt");
    Files.writeString(tmp, "hello selenium upload");
    driver.findElement(By.id("file-upload")).sendKeys(tmp.toAbsolutePath().toString());
    driver.findElement(By.id("file-submit")).click();
  }

  @Then("I see the uploaded filename")
  public void seeUploaded() {
    String name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")))
        .getText().trim();
    assertEquals("upload-demo.txt", name);
  }
}