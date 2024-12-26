@tag
  Feature: Purchase the order from Ecommerce website

    Background:
      Given I landed on Ecommerce page

    @Regression
    Scenario Outline:Positive test of Submitting the Order
      Given I logged in with username<username> and password <password>
      When I add product<productName> to Cart
      And Checkout<productName> and submit the order
      Then verify "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
      Examples:
        |username                    | password |productName|
        |meghana25chittti@gmail.com|Chitti@1998|BANARSI SAREE|