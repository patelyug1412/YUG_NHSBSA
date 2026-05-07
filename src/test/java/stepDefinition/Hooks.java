package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        try {
            String browser = System.getProperty("browser", "chrome");
            String gridUrl;

            if (browser.equalsIgnoreCase("chrome")) {
                gridUrl = "http://localhost:4444";
                //VNS Url = http://localhost:7900
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-dev-shm-usage");
                driver = new RemoteWebDriver(new URL(gridUrl), options);

            } else if (browser.equalsIgnoreCase("firefox")) {
                gridUrl = "http://localhost:4445";
                //VNS Url = http://localhost:7901
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-dev-shm-usage");
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
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}