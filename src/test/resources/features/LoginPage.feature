@loginPage
Feature: Verify Element section

  Scenario: TC-1: Verify Provider and Customer make a call and end call
    Given I open Customer and Provider page
    And I input custom page infor with name Jack and Submit
    And I input provider with username ypham0819@gmail.com and password Abc@1234
    And I select Call button
    And Provider end call
    And Customer end call

  Scenario: TC-2: Verify Provider and Customer have a chat
    Given I open Customer and Provider page
    And I input custom page infor with name Jack and Submit
    And I input provider with username ypham0819@gmail.com and password Abc@1234
    And I select chat icon
    And I type Can I hep you sentence
    Then I verify customer receive Can I hep you sentence
#    And I select Call button
#    And Provider end call
#    And Customer end call
#    And I enter user name jpham_csmanager@ecapital.com and password Enclave
