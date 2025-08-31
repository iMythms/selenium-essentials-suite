package com.mytham.essentials.tests;

import com.mytham.essentials.support.JUnitTestBase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.WindowType;

public class WindowsTest extends JUnitTestBase {

  @Test
  void switchToNewWindowByHandle() {
    driver.get("https://the-internet.herokuapp.com/windows");
    String original = driver.getWindowHandle();
    driver.findElement(By.linkText("Click Here")).click();

    Set<String> handles = driver.getWindowHandles();
    assertEquals(2, handles.size());
    for (String h : handles) {
      if (!h.equals(original)) {
        driver.switchTo().window(h);
        break;
      }
    }
    assertTrue(driver.getTitle().toLowerCase().contains("new window"));
    driver.close();
    driver.switchTo().window(original);
  }

  @Test
  void openNewTabWithSelenium4() {
    String original = driver.getWindowHandle();
    driver.switchTo().newWindow(WindowType.TAB);
    driver.get("https://www.selenium.dev/");
    assertTrue(driver.getTitle().toLowerCase().contains("selenium"));
    driver.close();
    driver.switchTo().window(original);
  }
}