Feature: Purchase the order from Ecommerce website

  Background:
    Given I landed on Ecommerce page

  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <userID> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> & submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples:
      | userID                  | password  | productName   |
      | anweshabachha@gmail.com | Bsdk@2018 | IPHONE 13 PRO |

