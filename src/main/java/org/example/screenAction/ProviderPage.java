package org.example.screenAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProviderPage {
    private static WebDriver driver;

    public ProviderPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void navigateTo(String url) {
        driver.get(url);
//        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(messageInputField));
    }
    public static void login(String name,String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.xpath("//*[contains(text(), 'For Providers')]")).click();
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Email']")));
        email.sendKeys(name);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='btnSignIn']")).click();
        WebElement gettingReady = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[contains(text(), 'Getting Ready')]")));
        gettingReady.click();
        WebElement callbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Call'][1]")));
        callbtn.click();
        driver.findElement(By.xpath("//*[contains(text(), 'Continue on this browser')]")).click();
        Thread.sleep(2000);
//        driver.navigate().back();
//        WebElement continueOnBrowser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Continue on this browser')]")));
//        continueOnBrowser.click();


        WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Continue')]")));
        continueBtn.click();
        //iframe[@id=jitsiConferenceFrame0]
  //      driver.switchTo().frame(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@id=jitsiConferenceFrame0]"))));
        driver.switchTo().frame(0);
        WebElement joinNowBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and text()='Join Now']")));
        joinNowBtn.click();
        driver.switchTo().defaultContent();

//
//        driver.findElement(By.xpath("//input[@value='Enter Waiting Room']")).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//        // Chờ cho phần tử Continue to browser
//        WebElement elementContinue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/div[1]/img")));
//        elementContinue.click();
//        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ReminderModal\"]/div/div/div[3]/button")));
//        element2.click();
//        WebElement elementJoinNow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Join Now']")));
//        elementJoinNow.click();

//        driver.findElement(passwordField).sendKeys(password);
//        driver.findElement(loginButton).click();
    }
}
