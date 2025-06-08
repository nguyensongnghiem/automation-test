package org.example.screenAction;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProviderPage {
    private static WebDriver driver;
    public ProviderPage(WebDriver driver) {
        this.driver = driver;
    }
    static ElementDefine elementDefine = new ElementDefine();
    static String forProviderBtn = "//*[contains(text(), 'For Providers')]";
    static String emailInput = "//input[@placeholder='Email']";
    static String passWordInput = "//input[@placeholder='Password']";
    static String signInBtn = "//button[@id='btnSignIn']";
    static String callBtn = "//a[@title='Call'][1]";
    static String continueBrowser = "//*[contains(text(), 'Continue on this browser')]";
    static String continueBtn = "//button[contains(text(), 'Continue')]";
    static String video = "//video[@id='largeVideo']";
    static String endVideoBtn = "//div[@class='toolbox-icon   hangup-button']";
    static String endVisitBtn = " //button[contains(text(),'End Visit for all')]";
    static String chatBtn = "//a[contains(@data-content,'Start a chat thread')][1]";
    static String chatTxtBox = "//input[@placeholder='Type your message here']";

    public static void navigateTo(String url) {
        driver.get(url);
    }
    public static void login(String name,String password) throws InterruptedException {
        elementDefine.getElement(driver,forProviderBtn).click();
        elementDefine.getElement(driver,emailInput).sendKeys(name);
        elementDefine.getElement(driver,passWordInput).sendKeys(password);
        elementDefine.getElement(driver,signInBtn).click();
    }
    public static void call() throws InterruptedException {
        Actions actions = new Actions(driver);
        Thread.sleep(5000);
        elementDefine.getElement(driver,callBtn).click();
        elementDefine.getElement(driver,continueBrowser).click();
     //   elementDefine.getElement(driver,continueBtn).click();
        Thread.sleep(8000);

    }
    public static void endCall() {
        driver.switchTo().frame(0);
        elementDefine.getElement(driver,endVideoBtn).click();
        driver.switchTo().defaultContent();
        elementDefine.getElement(driver,endVisitBtn).click();
    }

    public static void chat(){
        elementDefine.getElement(driver,chatBtn).click();
    }

    public static void typeMessage(String message) throws InterruptedException {
        WebElement e = elementDefine.getElement(driver,chatTxtBox);
        e.sendKeys(message);
        Thread.sleep(500);
        e.sendKeys(Keys.RETURN);

    }
}
