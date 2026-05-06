package stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObject.ResultPage;
import pageObject.SearchPage;
import static stepDefinition.Hooks.driver;

public class NegativeLocationSearchSteps{

    SearchPage searchPageObj = new SearchPage(driver);
    ResultPage resultPageObj = new ResultPage(driver);

    @When("I search for jobs with position {string} and invalid location {string}")
    public void iSearchForJobsWithPositionAndInvalidLocation(String position, String location) {
        searchPageObj.writeJobPosition(position);
        searchPageObj.writeJobLocation(location);
    }

    @Then("I should see a message for invalid location {string}")
    public void iShouldSeeAMessageForInvalidLocation(String expectedMessage) {
        String noLocationTitle = resultPageObj.sendInvalidLocationTitle();
        Assert.assertTrue((noLocationTitle.toLowerCase()).contains(expectedMessage.toLowerCase()));
    }
}
