package stepDefinition;

import io.cucumber.java.en.*;
import pageObject.ResultPage;
import pageObject.SearchPage;
import static stepDefinition.Hooks.driver;
import org.junit.Assert;


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

    @Then("I sort the results by {string}")
    public void iSortTheResultsBy(String sortType) {
        resultPageObj.sortJobType(sortType);
    }

    @And("I should see jobs found for {string} in {string}")
    public void iShouldSeeJobsFoundForIn(String position, String location) {
        String resultTitle = resultPageObj.sendSearchResultTitle();
        Assert.assertTrue((resultTitle.toLowerCase()).contains(position.toLowerCase()));
        Assert.assertTrue((resultTitle.toLowerCase()).contains(location.toLowerCase()));
    }

    @Then("I should see message {string}")
    public void iShouldSeeMessage(String expectedResult) {
        String resultTitle = resultPageObj.sendSearchResultTitle();
        Assert.assertTrue((resultTitle.toLowerCase()).contains(expectedResult.toLowerCase()));
    }

    @And("I should see results sorted by newest date posted")
    public void iShouldSeeResultsSortedByNewestDatePosted() {
        Assert.assertTrue("Jobs are not sorted by newest date posted",
                resultPageObj.areDatesSortedNewestFirst());
    }
}
