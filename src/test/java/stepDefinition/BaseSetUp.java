package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;


import static stepDefinition.Hooks.driver;

public class BaseSetUp {

    @Given("I am on the NHS Jobs search page {string}")
    public void iAmOnTheNHSJobsSearchPage(String url) {
        driver.get(url);
    }

    @And("Page with title {string} should be opened")
    public void pageWithTitleShouldBeOpened(String pageTitle) {
        Assert.assertEquals(driver.getTitle(), pageTitle);
    }
}
