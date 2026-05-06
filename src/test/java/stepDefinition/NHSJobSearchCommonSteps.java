package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pageObject.ResultPage;
import pageObject.SearchPage;
import static stepDefinition.Hooks.driver;

public class NHSJobSearchCommonSteps{

    SearchPage searchPageObj = new SearchPage(driver);
    ResultPage resultPageObj = new ResultPage(driver);

    @When("I search for jobs with position {string} and location {string}")
    public void iSearchForJobsWithPositionAndLocation(String position, String location) {
        searchPageObj.writeJobPosition(position);
        searchPageObj.writeJobLocation(location);
    }

    @And("I select distance {string}")
    public void iSelectDistance(String distance) {
        searchPageObj.selectJobDistance(distance);
    }

    @And("I click on more search options button")
    public void iClickOnMoreSearchOptionsButton() {
        searchPageObj.clickSearchOptionButton();
    }

    @And("I select pay range {string}")
    public void iSelectPayRange(String payRange) {
        searchPageObj.selectSalaryRange(payRange);
    }

    @And("I click on search button")
    public void iClickOnSearchButton() {
        searchPageObj.clickFinalSearch();
    }

    @And("I sort the results by {string}")
    public void iSortTheResultsBy(String sortType) {
        resultPageObj.sortJobType(sortType);
    }

    @Then("I should see jobs found for {string} in {string}")
    public void iShouldSeeJobsFoundForIn(String position, String location) {
        String resultTitle = resultPageObj.sendSearchResultTitle();
        Assert.assertTrue(resultTitle.toLowerCase().contains(position.toLowerCase()));
        Assert.assertTrue(resultTitle.toLowerCase().contains(location.toLowerCase()));

    }

    @Then("I should see message {string}")
    public void iShouldSeeMessage(String expectedResult) {
        String resultTitle = resultPageObj.sendSearchResultTitle();
        Assert.assertTrue(resultTitle.toLowerCase().contains(expectedResult.toLowerCase()));
    }
}
