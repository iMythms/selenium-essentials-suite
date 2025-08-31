@ui @dynamic
Feature: Handle dynamic content

  Scenario: Reveal and interact with dynamic elements
    Given I open the dynamic page
    When I add a box and reveal the input
    Then I can type into the revealed input