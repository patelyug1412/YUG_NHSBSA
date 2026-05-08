package pageObject;

import Utility.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResultPage {
    WebDriver driver;
    WaitUtils wait;

    private static final Logger logger = LoggerFactory.getLogger(ResultPage.class);

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
    @FindBy(xpath = "//li[starts-with(normalize-space(),'Date posted')]")
    List<WebElement> postedDates;
    @FindBy(xpath = "//a[@data-test='search-next-page']")
    WebElement nextResultPageButton;
    @FindBy(xpath = "//a[@data-test='search-previous-page']")
    WebElement previousResultPageButton;
    @FindBy(xpath = "//a[@data-test='search-result-job-title']")
    List<WebElement> allJobTitles;
    @FindBy(xpath="//a[@id='first-result-title']")
    WebElement firstJob;

    public void sortJobType(String sortType){
        wait.waitForVisibility(sortJobType);
        logger.info("Sorting job results by: {}", sortType);
        Select select = new Select(sortJobType);
        select.selectByVisibleText(sortType);
    }
    public String sendSearchResultTitle(){
        wait.waitForVisibility(searchResultTitle);
        logger.info("Search result title displayed");
        return searchResultTitle.getText();
    }
    public String sendInvalidLocationTitle(){
        wait.waitForVisibility(invalidLocation);
        logger.info("Invalid location message displayed");
        return invalidLocation.getText();
    }
    public List<String> postedDatesForFirstTenJobs(){
        List<String> printPostedDates = new ArrayList<>();
        for (WebElement date : postedDates) {
            printPostedDates.add(date.getText());
        }
        return printPostedDates;
    }

    public boolean areDatesSortedNewestFirst() {
        List<String> dateTexts = postedDatesForFirstTenJobs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        logger.info("Validating job posted dates are sorted newest first");

        for (int i = 0; i < dateTexts.size() - 1; i++) {
            String currentDateText = dateTexts.get(i)
                    .replace("Date posted:", "")
                    .trim();
            String nextDateText = dateTexts.get(i + 1)
                    .replace("Date posted:", "")
                    .trim();
            try {
                LocalDate currentDate = LocalDate.parse(currentDateText, formatter);
                LocalDate nextDate = LocalDate.parse(nextDateText, formatter);
                logger.info("Comparing current date: {} with next date: {}", currentDate, nextDate);

                if (currentDate.isBefore(nextDate)) {
                    logger.error("Dates are not sorted. Current date: {}, Next date: {}", currentDate, nextDate);
                    return false;
                }
            } catch (DateTimeParseException e) {
                logger.error("Unable to parse posted date. Current text", e);
                return false;
            }
        }
        logger.info("Job posted dates are sorted newest first");
        return true;
    }

    public void clickNextPage() {
        scrollToElement(nextResultPageButton);
        logger.info("Clicking on the next result page");
        nextResultPageButton.click();
    }

    public void clickPreviousPage() {
        scrollToElement(previousResultPageButton);
        logger.info("Clicking on the previous result page");
        previousResultPageButton.click();
    }
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public List<String> getFirstPageJobs(){
        List<String> firstPageJobTitles = new ArrayList<>();
        logger.info("Getting job titles for the first result page");
        for (int i = 0; i < 10 && i < allJobTitles.size(); i++) {
            firstPageJobTitles.add(allJobTitles.get(i).getText());
        }
        return firstPageJobTitles;
    }
    public List<String> getSecondPageJobs(){
        List<String> secondPageJobTitles = new ArrayList<>();
        logger.info("Getting job titles for the next result page");
        for (int i = 0; i < 10 && i < allJobTitles.size(); i++) {
            secondPageJobTitles.add(allJobTitles.get(i).getText());
        }
        return secondPageJobTitles;
    }
    public List<String> getCurrentPageJobs(){
        List<String> currentPageJobTitles = new ArrayList<>();
        logger.info("Getting job titles for the current result page");
        for (int i = 0; i < 10 && i < allJobTitles.size(); i++) {
            currentPageJobTitles.add(allJobTitles.get(i).getText());
        }
        return currentPageJobTitles;
    }

    public void clickOnTheFirstJobTitle(){
        scrollToElement(firstJob);
        logger.info("Clicking on the first job title");
        firstJob.click();
    }

}
