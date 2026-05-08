package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObject.ResultPage;
import pageObject.SearchPage;

import static stepDefinition.Hooks.driver;


public class FilterSteps {
    SearchPage searchPageObj;
    ResultPage resultPageObj;

    public FilterSteps() {
        searchPageObj = new SearchPage(driver);
        resultPageObj = new ResultPage(driver);
    }

    @And("I click contract type filter")
    public void iClickContractTypeFilter() {
        resultPageObj.clickContractTypeFilterBtn();
    }

    @And("I Select Permanent contract checkbox")
    public void iSelectPermanentContractCheckbox() {
        resultPageObj.clickCheckBoxPermanentContract();
    }

    @And("I click on the apply filter button")
    public void iClickOnTheApplyFilterButton() {
        resultPageObj.clickOnApplyFilterBtn();
    }

    @Then("the first ten jobs should have contract type permanent")
    public void theFirstTenJobsShouldHaveContractTypePermanent() {
        boolean allPermanentJob = resultPageObj.areAllJobsPermanent();
        Assert.assertTrue("All jobs are permanent", allPermanentJob);
    }

    @And("I click working pattern filter")
    public void iClickWorkingPatternFilter() {
        resultPageObj.clickWorkingPatternFilterBtn();
    }

    @And("I select Full time working pattern checkbox")
    public void iSelectFullTimeWorkingPatternCheckbox() {
        resultPageObj.clickFullTimeWorkingPattern();
    }

    @Then("the first ten jobs should have working pattern full time")
    public void theFirstTenJobsShouldHaveWorkingPatternFullTime() {
        boolean allFullTimeJob = resultPageObj.areAllJobsFullTime();
        Assert.assertTrue("All jobs are full time", allFullTimeJob);
    }

    @Then("the first ten jobs should have permanent contract or full time working pattern")
    public void theFirstTenJobsShouldHavePermanentContractOrFullTimeWorkingPattern() {
        boolean eitherPermanentOrFullTimeOrBoth = resultPageObj.areJobsEitherPermanentOrFullTime();
        Assert.assertTrue("Jobs are either Permanent or Full-time or both",
                eitherPermanentOrFullTimeOrBoth);
    }

    @Then("Check all filters are removed")
    public void checkAllFiltersAreRemoved() {
        boolean removedFilters = resultPageObj.areAllFiltersRemoved();
        Assert.assertTrue("All Filters are removed", removedFilters);
    }

    @And("I click on clear filter button")
    public void iClickOnClearFilterButton() {
        resultPageObj.clickClearFilterBtn();
    }
}
