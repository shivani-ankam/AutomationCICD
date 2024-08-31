
@tag
Feature: Error Validation
  I want to use this template for my feature file

  

  @ErrorValidations
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password" meassge is displayed 

    Examples: 
      | name             | password | 
      | shvank@gmail.com | shvAnk1  |
