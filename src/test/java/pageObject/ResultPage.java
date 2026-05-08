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
    @FindBy(xpath="//span[normalize-space()='Contract type']")
    WebElement contractTypeFilterBtn;
    @FindBy(xpath="//input[@id='contract-type-permanent']")
    WebElement checkBoxPermanentContract;
    @FindBy(xpath="//span[normalize-space()='Working pattern']")
    WebElement workingPatternFilterBtn;
    @FindBy(xpath="//input[@id='working-pattern-full-time']")
    WebElement checkboxFullTimeWorkingPattern;
    @FindBy(xpath="//input[@id='refine-search']")
    WebElement applyFilterBtn;
    @FindBy(xpath = "//li[@data-test='search-result']")
    List<WebElement> jobCards;
    @FindBy(xpath="//a[normalize-space()='Clear filters']")
    WebElement clearFilterBtn;

    public void clickClearFilterBtn(){
        scrollToElement(clearFilterBtn);
        logger.info("Clicking on the clear filter button");
        clearFilterBtn.click();
    }
    public boolean areAllJobsFullTime() {
        for (WebElement jobCard : jobCards) {
            String jobText = jobCard.getText();
            if (!jobText.contains("Full time")) {
                return false;
            }
        }
        return true;
    }

    public void clickWorkingPatternFilterBtn(){
        scrollToElement(workingPatternFilterBtn);
        logger.info("Clicking on the working pattern filter button");
        workingPatternFilterBtn.click();
    }
    public void clickFullTimeWorkingPattern(){
        scrollToElement(checkBoxPermanentContract);
        logger.info("Selecting the checkbox for Full-time working pattern");
        if (!checkboxFullTimeWorkingPattern.isSelected()){
            checkboxFullTimeWorkingPattern.click();
        }
    }

    public boolean areAllJobsPermanent() {
        for (WebElement jobCard : jobCards) {
            String jobText = jobCard.getText();
            if (!jobText.contains("Permanent")) {
                return false;
            }
        }
        return true;
    }
    public void clickContractTypeFilterBtn(){
        scrollToElement(contractTypeFilterBtn);
        logger.info("Clicking on the contract type filter button");
        contractTypeFilterBtn.click();
    }
    public void clickCheckBoxPermanentContract(){
        scrollToElement(checkBoxPermanentContract);
        logger.info("Selecting the checkbox for permanent contract");
        if (!checkBoxPermanentContract.isSelected()){
            checkBoxPermanentContract.click();
        }
    }
    public void clickOnApplyFilterBtn(){
        scrollToElement(applyFilterBtn);
        logger.info("Clicking on the apply filter button");
        applyFilterBtn.click();
    }

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
    public boolean areJobsEitherPermanentOrFullTime() {
        for (WebElement jobCard : jobCards) {
            String jobText = jobCard.getText();
            boolean hasPermanent =
                    jobText.contains("Permanent");
            boolean hasFullTime =
                    jobText.contains("Full time");
            if (!(hasPermanent || hasFullTime)) {
                return false;
            }
        }
        return true;
    }
    public Boolean areAllFiltersRemoved(){
        clickContractTypeFilterBtn();
        boolean permanentSelected = checkBoxPermanentContractButtonSelected();
        if (permanentSelected) {
            clickWorkingPatternFilterBtn();
            boolean fullTimeSelected = checkBoxFullTimeWorkingPatternSelected();
            return fullTimeSelected;
        }
        return false;
    }
    public Boolean checkBoxPermanentContractButtonSelected(){
        scrollToElement(checkBoxPermanentContract);
        logger.info("Verifying checkbox for permanent contract is selected or not");
        return !checkBoxPermanentContract.isSelected();
    }
    public Boolean checkBoxFullTimeWorkingPatternSelected(){
        scrollToElement(checkboxFullTimeWorkingPattern);
        logger.info("Verifying checkbox for full-time working pattern is selected or not");
        return !checkboxFullTimeWorkingPattern.isSelected();
    }
}
