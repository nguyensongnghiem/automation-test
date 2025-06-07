package org.example.steps;

import io.cucumber.java.en.Then;
import org.example.screenAction.CustomerPage;
import org.example.screenAction.ProviderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Configuration;

public class LoginSteps {
//    String baseUrl = Configuration.get().getTestProperty("baseUrl");
    @Then("I open Customer and Provider page")
    public void navigateToPages() {
        CustomerPage.navigateTo("https://team.vsee.me/u/robin3");
        ProviderPage.navigateTo("https://team.vsee.me/u/robin3");
       System.out.println("test");
    }
    @Then("^I input custom page infor with name (.*) and Submit")
    public void inputCustomerPage(String name) throws InterruptedException {
        CustomerPage.login(name);
    }

    @Then("^I input prodiver with username (.*) and password (.*)")
    public void loginProviderPage(String username,String password) throws InterruptedException {
        ProviderPage.login(username,password);
    }

//    @Then("^I enter user name (.*) and password (.*)")
//    public void iEnterUserAndPass(String user, String pass) throws InterruptedException {
//        Thread.sleep(500);
//      WebElement userField = Driver.driver.findElement(By.xpath("//input[@type='email']"));
//      userField.click();
//      userField.sendKeys(user);
//    }
}
