@loginPage
Feature: Verify Element section

  Scenario: ECLCS-T4: Verify the ability to login into the Eclipse-case system if input correctly Email and Password
    And I open Customer and Provider page
    And I input custom page infor with name Jack and Submit
    And I input prodiver with username ypham0819@gmail.com and password Abc@1234
#    And I enter user name jpham_csmanager@ecapital.com and password Enclave
