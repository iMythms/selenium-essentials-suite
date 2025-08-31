@api @smoke
Feature: API health

  Scenario: httpbin GET returns 200
    When I GET "https://httpbin.org/status/200"
    Then the response status should be 200

  Scenario: httpbin GET returns body url
    When I GET "https://httpbin.org/get"
    Then the json path "url" should equal "https://httpbin.org/get"