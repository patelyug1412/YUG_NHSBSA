package stepDefinition;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Utility.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;

public class Hooks {

    public static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup() {
        logger.info("Starting test execution");
        try {
            String browser = System.getProperty("browser", ConfigReader.getProperty("browser"));
            logger.info("Browser selected: {}", browser);

            if (browser.equalsIgnoreCase("chrome")) {
                String gridUrl = ConfigReader.getProperty("chrome.grid.url");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-dev-shm-usage");
                logger.info("Connecting to Chrome Grid: {}", gridUrl);
                driver = new RemoteWebDriver(new URL(gridUrl), options);

            } else if (browser.equalsIgnoreCase("firefox")) {
                String gridUrl = ConfigReader.getProperty("firefox.grid.url");
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-dev-shm-usage");
                logger.info("Connecting to Firefox Grid: {}", gridUrl);
                driver = new RemoteWebDriver(new URL(gridUrl), options);

            } else {
                throw new RuntimeException("Invalid browser name: " + browser);
            }
            driver.manage().window().maximize();

        } catch (Exception e) {
            throw new RuntimeException("RemoteWebDriver setup failed", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario failed: {}", scenario.getName());
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            logger.info("Screenshot attached for failed scenario");
        }

        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
        }
    }
}