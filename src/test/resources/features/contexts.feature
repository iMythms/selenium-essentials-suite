@ui @contexts
Feature: Work with windows and iframes

  Scenario: Switch to new window
    Given I open the windows page
    When I open a new window
    Then I can assert the new window title and return

  Scenario: Edit inside an iframe
    Given I open the iframe page
    When I type "Hello Frame!" inside the editor
    Then I see the text inside the editor