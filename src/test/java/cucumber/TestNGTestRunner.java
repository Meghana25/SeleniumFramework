package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;// required for testng
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber",glue = "webautomation.stepDefinations",
        monochrome = true,tags = "@Regression", plugin={"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
