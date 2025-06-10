package org.example.steps;// eClipseAutomation/driver/BaseDriver.java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BaseDriver {

    private WebDriver driver; // Mỗi instance của BaseDriver sẽ có một WebDriver riêng
    private static final String GRID_HUB_URL = "http://localhost:4444/wd/hub";
    public BaseDriver() {
        // Constructor, có thể không cần làm gì ở đây nếu getDriver() tự khởi tạo
    }

    public WebDriver getDriver(String browserType) {
        try {

            switch (browserType.toLowerCase()) {
                case "chrome":
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
                    prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
                    prefs.put("profile.default_content_setting_values.geolocation", 1);
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
                    prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
                    prefs.put("profile.default_content_setting_values.geolocation", 1);
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    WebDriverManager.chromedriver().setup(); // Vẫn dùng WebDriverManager nếu chạy local
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    if (isGridRunning(GRID_HUB_URL)) { // Kiểm tra Grid có sẵn không
                        driver = new RemoteWebDriver(new URL(GRID_HUB_URL), chromeOptions);
                        System.out.println("Initialized Chrome driver via Grid.");
                    } else {
                        driver = new ChromeDriver(chromeOptions);
                        System.out.println("Initialized Chrome driver locally.");
                    }
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup(); // Vẫn dùng WebDriverManager nếu chạy local
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("media.navigator.enabled", true);
                    firefoxOptions.addPreference("permissions.default.camera", 1);  // 1: Cho phép, 2: Hỏi, 3: Từ chối
                    firefoxOptions.addPreference("permissions.default.microphone", 1);


                    if (isGridRunning(GRID_HUB_URL)) { // Kiểm tra Grid có sẵn không
                        driver = new RemoteWebDriver(new URL(GRID_HUB_URL), firefoxOptions);
                        System.out.println("Initialized Firefox driver via Grid.");
                    } else {
                        driver = new FirefoxDriver(firefoxOptions);
                        System.out.println("Initialized Firefox driver locally.");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + browserType + ". Please choose 'chrome' or 'firefox'.");
            }
            // Mở maximize cửa sổ sau khi driver được tạo (áp dụng cho cả local và remote)
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver for " + browserType + ": " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver for " + browserType, e);
        }
        return driver;
    }
    private boolean isGridRunning(String hubUrl) {
        try {

            URL url = new URL(hubUrl + "/status"); // Grid 4+ có endpoint /status

            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000); // 2 giây timeout kết nối
            connection.setReadTimeout(2000);    // 2 giây timeout đọc dữ liệu
            connection.connect();

            int responseCode = connection.getResponseCode();
            // Nếu Hub trả về 200 OK, có thể Hub đang chạy
            if (responseCode == 200) {

                String responseBody = new java.io.BufferedReader(
                        new java.io.InputStreamReader(connection.getInputStream()))
                        .lines().collect(java.util.stream.Collectors.joining());
                // Kiểm tra xem phản hồi có chứa một dấu hiệu của Hub không (ví dụ: "ready")
                return responseBody.contains("ready") || responseBody.contains("Grid is running");
            }
            return false;
        } catch (Exception e) {
            // Lỗi kết nối nghĩa là Hub không chạy hoặc URL sai
             System.out.println("Grid Hub not reachable at " + hubUrl + ": " + e.getMessage());
            return false;
        }
    }

    public void openWebPage(String url) {
        driver.get(url); // Mở URL trên driver của instance này
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}