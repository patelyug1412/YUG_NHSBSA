package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObject.SearchPage;


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

    @And("I click on accept the cookies")
    public void iClickOnAcceptTheCookies() {
        SearchPage searchPageObj = new SearchPage(driver);
        searchPageObj.clickAcceptCookie();
    }
}
