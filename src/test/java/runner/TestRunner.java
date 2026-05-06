package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/nhs_job_search.feature",
        glue = "stepDefinition",
        plugin = {"pretty", "html:src/test/resources/cucumber-report.html"},
        monochrome = true
)

public class TestRunner {
}
