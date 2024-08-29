
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page
  
  @Regreesion
   Scenario Outline: Positive Test of submitting the order
   
   
    Given Logged in with username <name> and password <password>
    When I add product <ProductName> to cart
    And Checkout <ProductName> and Submit the order 
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage 

    Examples: 
      | name  							 | password | ProductName  |
      | manojg1413@gmail.com | Manoj@18 | ZARA COAT 3 |
     