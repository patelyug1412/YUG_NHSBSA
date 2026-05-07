package pageObject;

import Utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        logger.info("Validating job posted dates");
        for (int i = 0; i < dateTexts.size() - 1; i++) {
            String currentDateText = dateTexts.get(i)
                    .replace("Date posted:", "")
                    .trim();
            String nextDateText = dateTexts.get(i + 1)
                    .replace("Date posted:", "")
                    .trim();

            LocalDate currentDate = LocalDate.parse(currentDateText, formatter);
            LocalDate nextDate = LocalDate.parse(nextDateText, formatter);

            if (currentDate.isBefore(nextDate)) {
                return false;
            }
        }
        return true;
    }

}
