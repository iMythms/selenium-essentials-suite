@ui @form
Feature: Submit the Selenium demo web form

  Scenario Outline: Fill and submit form successfully
    Given I am on the web form
    When I fill the form with "<text>" "<option>" <check> <radio> "<date>"
    And I submit the form
    Then I should see a non-empty success message

    Examples:
      | text           | option | check | radio | date       |
      | Selenium Demo  | Two    | true  | 2     | 2024-12-31 |
      | Hello World    | One    | false | 1     | 2025-01-01 |