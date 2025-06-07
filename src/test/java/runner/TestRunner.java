package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import cucumber.api.junit.Cucumber;
//import io.cucumber.testng.AbstractTestNGCucumberTests;

import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions

        (features = {"src/test/resources/features"},
                glue = {"org.example.steps"},
  //             tags ="@loginPage",
//                plugin = {"pretty", "html:cucumber-html-reports",
//                        "json:cucumber-html-reports/cucumber.json"}
                plugin = {"pretty", "html:target/cucumber/report.html",
                        "json:target/cucumber/report.json"}
        )

public class TestRunner {
}
