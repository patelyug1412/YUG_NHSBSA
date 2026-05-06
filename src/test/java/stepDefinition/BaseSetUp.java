package stepDefinition;

import io.cucumber.java.en.Given;

import static stepDefinition.Hooks.driver;

public class BaseSetUp {

    @Given("I am on the NHS Jobs search page {string}")
    public void iAmOnTheNHSJobsSearchPage(String url) {
        driver.get(url);
    }

}
