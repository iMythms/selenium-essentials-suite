package com.mytham.essentials.support;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

public abstract class JUnitTestBase {
  protected WebDriver driver;

  @RegisterExtension
  ScreenshotOnFailure screenshot = new ScreenshotOnFailure();

  @BeforeEach
  void open() {
    driver = DriverManager.create();
  }

  @AfterEach
  void close() {
    DriverManager.quit();
  }
}
