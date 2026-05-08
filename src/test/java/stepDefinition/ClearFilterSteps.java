package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObject.SearchPage;
import static stepDefinition.Hooks.driver;

public class ClearFilterSteps {
    SearchPage searchPageObj;

    public ClearFilterSteps() {
        searchPageObj = new SearchPage(driver);
    }

    @And("I clear all selected filters")
    public void iClearAllSelectedFilters() {
        searchPageObj.clickClearFilterButton();
    }

    @Then("the search filters should be cleared")
    public void theSearchFiltersShouldBeCleared() {
        Assert.assertEquals("Position Field Is Empty",
                searchPageObj.getPositionFiledValue(),"");
        Assert.assertEquals("Location Field Is Empty",
                searchPageObj.getLocationFiledValue(),"");
        Assert.assertEquals("Distance is not Selected",
                searchPageObj.getSelectedDistance(),"All locations");
    }
}
