package org.example.screenAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;

public class CustomerPage {
    private static WebDriver driver;

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }
    public static void navigateTo(String url) {
        driver.get(url);
//        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(messageInputField));
    }

    public static void login(String name) throws InterruptedException {
     //   driver.findElement(By.xpath("//*[contains(text(), 'For Providers')]")).click();
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='consent']")).click();


        driver.findElement(By.xpath("//input[@value='Enter Waiting Room']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        // Chờ cho phần tử Continue to browser
        WebElement elementContinue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/div[1]/img")));
        elementContinue.click();
        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ReminderModal\"]/div/div/div[3]/button")));
        element2.click();

   //     WebElement elementJoinNow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Join Now']")));
    //       elementJoinNow.click();

//        driver.findElement(passwordField).sendKeys(password);
//        driver.findElement(loginButton).click();
    }
}
