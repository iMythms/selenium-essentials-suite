package com.mytham.essentials.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class ApiSteps {
  private Response resp;

  @When("I GET {string}")
  public void i_get(String url) {
    resp = given().when().get(url);
  }

  @Then("the response status should be {int}")
  public void statusIs(int code) {
    assertEquals(code, resp.getStatusCode());
  }

  @Then("the json path {string} should equal {string}")
  public void jsonEquals(String path, String expected) {
    resp.then().body(path, equalTo(expected));
  }
}