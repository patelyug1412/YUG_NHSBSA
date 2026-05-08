package pageObject;

import Utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobDetailPage {
    WebDriver driver;
    WaitUtils wait;

    private static final Logger logger = LoggerFactory.getLogger(pageObject.ResultPage.class);

    public JobDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@id='apply-ats-direct']")
    WebElement applyButton;

    public String getPageTitle() {
        logger.info("Getting page title for the job detail page");
        return driver.getTitle();
    }

    public Boolean checkApplyBtn(){
        wait.waitForVisibility(applyButton);
        return applyButton.isDisplayed();
    }
}
