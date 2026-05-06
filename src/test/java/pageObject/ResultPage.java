package pageObject;

import Utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ResultPage {
    WebDriver driver;
    WaitUtils wait;

    public ResultPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//select[@id='sort']")
    WebElement sortJobType;
    @FindBy(xpath="//h1[@id='search-results-heading']")
    WebElement searchResultTitle;
    @FindBy(xpath="//h2[normalize-space()='Location not found']")
    WebElement invalidLocation;

    public void sortJobType(String sortType){
        wait.waitForVisibility(sortJobType);
        Select select = new Select(sortJobType);
        select.selectByVisibleText(sortType);
    }
    public String sendSearchResultTitle(){
        wait.waitForVisibility(searchResultTitle);
        return searchResultTitle.getText();
    }
    public String sendInvalidLocationTitle(){
        wait.waitForVisibility(invalidLocation);
        return invalidLocation.getText();
    }
}
