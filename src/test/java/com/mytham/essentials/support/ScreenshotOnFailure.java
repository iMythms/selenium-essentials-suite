package com.mytham.essentials.support;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotOnFailure implements TestWatcher {
  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    try {
      var driver = DriverManager.get();
      if (driver instanceof TakesScreenshot ts) {
        var file = ts.getScreenshotAs(OutputType.FILE);
        var dir = new File("target/screenshots");
        dir.mkdirs();
        var name = context.getRequiredTestMethod().getName() + "-"
            + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".png";
        Files.copy(file.toPath(), new File(dir, name).toPath());
      }
    } catch (Exception ignored) {

    }
  }
}
