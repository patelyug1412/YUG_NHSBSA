package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObject.ResultPage;
import pageObject.SearchPage;

import java.util.ArrayList;
import java.util.List;

import static stepDefinition.Hooks.driver;

public class PaginationVerificationSteps {

    ResultPage resultPageObj;
    List<String> firstPageJobTitles = new ArrayList<>();
    List<String> secondPageJobTitles = new ArrayList<>();
    List<String> currentPageJobTitles = new ArrayList<>();

    public PaginationVerificationSteps() {
        resultPageObj = new ResultPage(driver);
    }

    @And("I should store the set of jobs for first page")
    public void iShouldStoreTheSetOfJobsForFirstPage() {
        firstPageJobTitles = resultPageObj.getFirstPageJobs();
    }

    @When("I go to the next page of results")
    public void iGoToTheNextPageOfResults() {
        resultPageObj.clickNextPage();
    }

    @Then("I should store the set of jobs for second page")
    public void iShouldStoreTheSetOfJobsForSecondPage() {
        secondPageJobTitles = resultPageObj.getSecondPageJobs();
    }

    @And("First and second page jobs should be different")
    public void firstAndSecondPageJobsShouldBeDifferent() {
        Assert.assertNotEquals("Job list on the both pages are different",
                firstPageJobTitles, secondPageJobTitles);
    }

    @When("I go back to the previous page of results")
    public void iGoBackToThePreviousPageOfResults() {
        resultPageObj.clickPreviousPage();
    }

    @Then("I should store the set of jobs for first page again")
    public void iShouldStoreTheSetOfJobsForFirstPageAgain() {
        currentPageJobTitles = resultPageObj.getCurrentPageJobs();
    }

    @And("Current and first page job should be same")
    public void currentAndFirstPageJobShouldBeSame() {
        Assert.assertEquals("Job list on both pages should be same",
                firstPageJobTitles, currentPageJobTitles);
    }
}
