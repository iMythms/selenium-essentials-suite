package com.mytham.essentials.steps;

import com.mytham.essentials.pages.WebFormPage;
import com.mytham.essentials.support.DriverManager;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class WebFormSteps {
  private WebFormPage page;

  @Given("I am on the web form")
  private void i_am_on_the_web_form() {
    page = new WebFormPage(DriverManager.get()).open();
  }

  @When("I fill the form with {string} {string} {word} {int} {string}")
  public void i_fill_form(String text, String option, String check, Integer radio, String date) {
    page.setText(text)
        .setPassword("secret")
        .setTextarea("notes")
        .selectOptionByText(option)
        .setCheckbox(Boolean.parseBoolean(check))
        .selectRadio(radio)
        .setColor("#00ff00")
        .setDate(date);
  }

  @When("I submit the form")
  public void i_submit() {
    page.submit();
  }

  @Then("I should see a non-empty message")
  public void i_should_see_message() {
    assertFalse((page.getMessage().isEmpty()));
  }
}
