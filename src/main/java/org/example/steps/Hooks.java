package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.screenAction.CustomerPage;
import org.example.screenAction.ProviderPage;


public class Hooks {
    public static BaseDriver customerBrowser;
    public static BaseDriver providerBrowser;

    // Khai báo các Page Object
    public static CustomerPage customerPage;
    public static ProviderPage providerPage;

        @Before(order = 0)
        public void setupScenerio(){
            customerBrowser = new BaseDriver();
            providerBrowser = new BaseDriver();

            // Khởi tạo Page Objects với các driver tương ứng
            customerPage = new CustomerPage(customerBrowser.getDriver());
            providerPage = new ProviderPage(providerBrowser.getDriver());

//            Driver driver = new Driver();
           // WebDriverManager.firefoxdriver().browserVersion("139.0.1").setup();
           // driver = new FirefoxDriver();
//            driver = new ChromeDriver();
//            String baseUrl = Configuration.get().getTestProperty("baseURL");
//            // launch Fire fox and direct it to the Base URL
//            driver.get(baseUrl);
//            String baseUrl = Configuration.get().getTestProperty("baseURL");
//            driver.get(baseUrl);
//            Driver.openWebPage();

        }
//        @After(order=0)
//        public void close(){
//            if (customerBrowser != null) {
//                customerBrowser.quitDriver();
//            }
//            if (providerBrowser != null) {
//                providerBrowser.quitDriver();
//            }
//        }
    }

