package com.mytham.essentials.tests;

import com.mytham.essentials.pages.WebFormPage;
import com.mytham.essentials.support.JUnitTestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class WebFormPOMTest extends JUnitTestBase {

  @ParameterizedTest
  @CsvSource({
      "Selenium Demo, Two, true,  2, 2024-12-31",
      "Hello World,  One, false, 1, 2025-01-01"
  })
  void formHappyPaths(String text, String option, boolean check, int radioIndex, String date) {
    WebFormPage page = new WebFormPage(driver)
        .open()
        .setText(text)
        .setPassword("secret")
        .setTextarea("notes")
        .selectOptionByText(option)
        .setCheckbox(check)
        .selectRadio(radioIndex)
        .setColor("#00ff00")
        .setDate(date)
        .submit();

    assertFalse(page.getMessage().isEmpty());
    assertEquals("Two".equals(option) ? "2" : "1", page.selectedOptionValue());
  }
}
