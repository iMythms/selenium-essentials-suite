package com.mytham.essentials.tests;

import com.mytham.essentials.support.JUnitTestBase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class FileUploadTest extends JUnitTestBase {

  @Test
  void canUploadAFile() throws Exception {
    driver.get("https://the-internet.herokuapp.com/upload");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    Path tmp = Paths.get(System.getProperty("java.io.tmpdir"), "upload-demo.txt");
    Files.writeString(tmp, "hello selenium upload");

    driver.findElement(By.id("file-upload"))
        .sendKeys(tmp.toAbsolutePath().toString());
    driver.findElement(By.id("file-submit")).click();

    WebElement uploaded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
    assertEquals("upload-demo.txt", uploaded.getText().trim());
  }
}