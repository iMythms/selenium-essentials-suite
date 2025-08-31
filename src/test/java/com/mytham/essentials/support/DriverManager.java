package com.mytham.essentials.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
  private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

  public static WebDriver get() {
    return TL.get();
  }

  public static WebDriver create() {
    ChromeOptions opts = new ChromeOptions();
    // Always set a sane window size for predictable element positions
    opts.addArguments("--window-size=1920,1080");

    // If running headless (CI), add common CI-friendly flags
    if ("1".equals(System.getenv("HEADLESS")) || Boolean.getBoolean("headless")) {
      opts.addArguments("--headless=new");
      opts.addArguments("--no-sandbox");
      opts.addArguments("--disable-dev-shm-usage");
      opts.addArguments("--disable-gpu");
    }

    WebDriver driver = new ChromeDriver(opts); // Selenium Manager, to resolve driver.
    TL.set(driver);
    return driver;
  }

  public static void quit() {
    WebDriver d = TL.get();
    if (d != null) {
      try {
        d.quit();
      } catch (Exception ignored) {
      } finally {
        TL.remove();
      }
    }
  }
}