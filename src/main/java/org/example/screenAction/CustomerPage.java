package org.example.screenAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;

public class CustomerPage {
    private static WebDriver driver;
    static ElementDefine elementDefine = new ElementDefine();
    static String firstName = "//input[@name='first_name']";
    static String agreeCheckbox = "//input[@name='consent']";
    static String btnEnterRoom = "//input[@value='Enter Waiting Room']";
    static String continueBrowser = "//*[contains(text(), 'Continue on this browser')]";
    static String continueBtn = "//button[contains(text(), 'Continue')]";
    static String endVideoBtn = "//div[@class='toolbox-icon   hangup-button']";
    static String endVisitBtn = "//a[contains(text(),'End Visit')]";
    static String chatTxt = "//div[@class='webchat-message-bubble']";


    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }
    public static void navigateTo(String url) {
        driver.get(url);
    }

    public static void submitCustomerRequest(String name) throws InterruptedException {
        Thread.sleep(500);
        elementDefine.getElement(driver,firstName).click();
        elementDefine.getElement(driver,firstName).sendKeys(name);
        elementDefine.getElement(driver,agreeCheckbox).click();
        elementDefine.getElement(driver,btnEnterRoom).click();
        elementDefine.getElement(driver,continueBrowser).click();
        Thread.sleep(500);
    }

    public static void endCall(){
        driver.switchTo().frame(0);
        elementDefine.getElement(driver,endVideoBtn).click();
        driver.switchTo().defaultContent();
        elementDefine.getElement(driver,endVisitBtn).click();
    }

    public static void verifyMessageFromProvider(String message){
        assertEquals(elementDefine.getElement(driver,chatTxt).getText(),message);
    }
}
