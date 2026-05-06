package stepDefinition;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pageObject.ResultPage;
import pageObject.SearchPage;
import static stepDefinition.Hooks.driver;

public class NegativePositionSearchSteps{

    SearchPage searchPageObj = new SearchPage(driver);
    ResultPage resultPageObj = new ResultPage(driver);

    @When("I search for jobs with invalid position {string} and location {string}")
    public void iSearchForJobsWithInvalidPositionAndLocation(String position, String location) {
        searchPageObj.writeJobPosition(position);
        searchPageObj.writeJobLocation(location);
    }

    @Then("I should see a message for invalid position {string}")
    public void iShouldSeeAMessageForInvalidPosition(String expectedMessage) {
        String resultTitle = resultPageObj.sendSearchResultTitle();
        Assert.assertTrue(resultTitle.toLowerCase().contains(expectedMessage.toLowerCase()));
    }

}
