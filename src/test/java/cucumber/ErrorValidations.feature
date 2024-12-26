@tag
Feature: Error Validations

  Background:
    Given I landed on Ecommerce page
    ##update few changes to check github

 @ErrorValidation
  Scenario Outline:Negative test of Submitting the Order
    Given I logged in with username<username> and password <password>
    Then verify "Incorrect email or password." message is displayed
    Examples:
      |username                    | password |productName|
      |meghana25chitti@gmail.com|Chitti@1998|BANARSI SAREE|