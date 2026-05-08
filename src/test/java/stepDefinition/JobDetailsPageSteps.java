package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObject.JobDetailPage;
import pageObject.ResultPage;
import pageObject.SearchPage;

import static stepDefinition.Hooks.driver;


public class JobDetailsPageSteps {

    ResultPage resultPageObj;
    JobDetailPage jobDetailPageObj;

    public JobDetailsPageSteps() {
        resultPageObj = new ResultPage(driver);
        jobDetailPageObj = new JobDetailPage(driver);
    }
    @When("I click on the first job result")
    public void iClickOnTheFirstJobResult() {
        resultPageObj.clickOnTheFirstJobTitle();
    }

    @Then("I should be redirected to the page with title {string}")
    public void iShouldBeRedirectedToThePageWithTitle(String expectedTitle ) {
        String actualTitle = jobDetailPageObj.getPageTitle();
        Assert.assertEquals("Page title are same",
                expectedTitle,actualTitle);
    }

    @And("The apply button should be visible")
    public void theApplyButtonShouldBeVisible() {
        Assert.assertTrue("Apply button is visible on the job detail page",
                jobDetailPageObj.checkApplyBtn());
    }

}
