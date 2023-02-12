package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featureFiles"},
        plugin = {"io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm"})

public class TestRunner {
}
