
@tag
Feature: ErrorValidation
  I want to use this template for my feature file

 

  @tErrorValidation
  Scenario Outline: Title of your scenario outline
   Given I landed on ECommerce page 
    When logged in with userName  <name> and password <pwd>
  
    Then "Incorrect email or password." message is diplayed

   Examples: 
      | name 								  | pwd 	|
      | mariam.kaba@gmail.com |Udemy	|
