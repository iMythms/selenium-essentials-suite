@ui @upload
Feature: Upload a file

  Scenario: Upload a small text file
    Given I open the upload page
    When I upload a small temp file
    Then I see the uploaded filename