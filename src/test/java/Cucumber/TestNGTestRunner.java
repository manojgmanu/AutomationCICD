package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumber -> TestNG , JUnit
@CucumberOptions(features = "src/test/java/Cucumber", glue = "manoj.stepDefination", monochrome = true, tags = "@Regreesion", plugin = {
		"html:target/Cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
