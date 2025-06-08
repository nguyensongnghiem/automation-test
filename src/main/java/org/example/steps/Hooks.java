package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.screenAction.CustomerPage;
import org.example.screenAction.ProviderPage;


public class Hooks {
    public static BaseDriver customerBrowser;
    public static BaseDriver providerBrowser;

    // Khai báo các Page Object
    public static CustomerPage customerPage;
    public static ProviderPage providerPage;
    private static final String NO_BROWSER_TAG = "@no_browser_needed";
        @Before(order = 0)
        public void setupScenerio(Scenario scenario){
            if (scenario.getSourceTagNames().contains(NO_BROWSER_TAG)) {
                System.out.println("HOOKS: Scenario tagged with '" + NO_BROWSER_TAG + "'. Skipping browser initialization.");
                customerBrowser = null; // Đảm bảo driver là null
                providerBrowser = null; // Đảm bảo driver là null
                customerPage = null; // Page objects cũng cần là null hoặc xử lý riêng
                providerPage = null;
                return; // Thoát sớm khỏi hook
            } else {
                // Khởi tạo cả hai trình duyệt mặc định
                customerBrowser = new BaseDriver();
                providerBrowser = new BaseDriver();

                // Khởi tạo Page Objects với các driver tương ứng
                customerPage = new CustomerPage(customerBrowser.getDriver());
                providerPage = new ProviderPage(providerBrowser.getDriver());
            }
//
        }
        @After(order=0)
       public void closeBrowser(Scenario scenario) { // Thêm tham số Scenario
        // Chỉ đóng trình duyệt nếu nó đã được khởi tạo và scenario không có tag @no_browser_needed
        if (!scenario.getSourceTagNames().contains(NO_BROWSER_TAG)) {
            if (customerBrowser != null) {
                System.out.println("HOOKS: Quitting customer browser.");
                customerBrowser.quitDriver();
                customerBrowser = null;
            }
            if (providerBrowser != null) {
                System.out.println("HOOKS: Quitting provider browser.");
                providerBrowser.quitDriver();
                providerBrowser = null;
            }
        } else {
             System.out.println("HOOKS: Skipping browser teardown for '" + NO_BROWSER_TAG + "' scenario.");
        }
    }
    }

