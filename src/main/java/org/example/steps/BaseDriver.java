package org.example.steps;// eClipseAutomation/driver/BaseDriver.java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BaseDriver {

    private WebDriver driver; // Mỗi instance của BaseDriver sẽ có một WebDriver riêng

    public BaseDriver() {
        // Constructor, có thể không cần làm gì ở đây nếu getDriver() tự khởi tạo
    }

    public WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup(); // Setup ChromeDriver
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.media_stream_camera", 1); // Cho phép camera
            prefs.put("profile.default_content_setting_values.media_stream_mic", 1);    // Cho phép microphone (nếu cần)
            prefs.put("profile.default_content_setting_values.geolocation", 1);       // Cho phép vị trí (nếu cần)
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options.setExperimentalOption("prefs", prefs);
            // options.addArguments("--headless"); // Thêm nếu bạn muốn chạy headless
            // options.addArguments("--window-size=1920,1080"); // Cần thiết cho headless
            driver = new ChromeDriver(options);
//            driver = new FirefoxDriver();
//            driver.manage().window().maximize();
        }
        return driver;
    }

    public void openWebPage(String url) {
        getDriver().get(url); // Mở URL trên driver của instance này
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}