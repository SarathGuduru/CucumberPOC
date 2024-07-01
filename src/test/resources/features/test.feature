
Feature: First feature file for POC
Description: Test

Background: Launch zoho application
  Given I launch Zoho application

@Regression
Scenario: First scenario for POC
  When I click on SignIn link
  Then verify that SignIn page is displayed
  And I enter 'sg0863@gmail.com' and click on Next button
  And I enter password and click on Next button
  Then I click on SignIn button

@Smoke
Scenario: Second scenario for POC
  When I click on SignIn link
