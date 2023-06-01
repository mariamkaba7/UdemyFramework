
@tag
Feature: Purchase item on ECommerce website 
  I want to use this template for my feature file
Background:
Given I landed on ECommerce page
@Regression 
   Scenario Outline:  Positive test of submitting the order 
    Given logged in with userName  <name> and password <pwd>
    When I add product  <productName> to cart
    And checkout <productName> and submit the order 
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page 

    Examples: 
      | name 								  | pwd 			| productName|
      | mariam.kaba@gmail.com |Udemy2023	|ZARA COAT 3 |
