package com.mytham.essentials.support;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CucumberHooks {
  @Before
  public void open() {
    DriverManager.create();
  }

  @After
  public void close(Scenario scenario) {
    var driver = DriverManager.get();
    try {
      if (scenario.isFailed() && driver instanceof TakesScreenshot ts) {
        byte[] png = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(png, "image/png", "screenshot");
      }
    } catch (Exception ignored) {
      // swallow screenshot errors
    } finally {
      DriverManager.quit();
    }
  }
}
