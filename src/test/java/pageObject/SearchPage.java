package pageObject;

import Utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepDefinition.Hooks;

public class SearchPage {
    WebDriver driver;
    WaitUtils wait;
    private static final Logger logger = LoggerFactory.getLogger(SearchPage.class);

    public SearchPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//button[contains(text(),'Accept additional cookies') or contains(text(),'Accept cookies') or @id='accept-cookies']")
    WebElement acceptCookiesButton;
    @FindBy(xpath="//input[@id='keyword']")
    WebElement jobPosition;
    @FindBy(xpath="//input[@id='location']")
    WebElement jobLocation;
    @FindBy(xpath="//select[@id='distance']")
    WebElement jobDistance;
    @FindBy(xpath="//a[@id='searchOptionsBtn']")
    WebElement searchOptionBtn;
    @FindBy(xpath="//select[@id='payRange']")
    WebElement jobSalaryRange;
    @FindBy(xpath="//input[@id='search']")
    WebElement searchButton;

    public void clickAcceptCookie(){
        try {
            wait.waitForClickability(acceptCookiesButton);
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println("Cookie banner not displayed");
        }
    }
    public void writeJobPosition(String jobPos){
        wait.waitForVisibility(jobPosition);
        jobPosition.clear();
        jobPosition.sendKeys(jobPos);
    }
    public void writeJobLocation(String jobLoc){
        jobLocation.clear();
        jobLocation.sendKeys(jobLoc);
    }
    public void selectJobDistance(String jobDis){
        wait.waitForVisibility(jobDistance);
        Select select = new Select(jobDistance);
        select.selectByValue(jobDis);
    }
    public void clickSearchOptionButton(){
        wait.waitForClickability(searchOptionBtn);
        searchOptionBtn.click();
    }
    public void selectSalaryRange(String jobSal){
        wait.waitForVisibility(jobSalaryRange);
        Select select = new Select(jobSalaryRange);
        select.selectByValue(jobSal);
    }
    public void clickFinalSearch(){
        wait.waitForClickability(searchButton);
        logger.info("Clicking search button");
        searchButton.click();
    }

}
