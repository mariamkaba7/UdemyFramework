package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="rahulshettyacademy.StepDefinitions",monochrome=true,tags = "@Regression", plugin={"html:target/cucumber.htm"})
// this will map feature file and stepdefinition and repport the result in html format in target file create in cucumber package 

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
