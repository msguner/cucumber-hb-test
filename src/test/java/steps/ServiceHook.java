package steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ThreadLocalDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ServiceHook {
    protected static WebDriver driver;
    Logger LOGGER = Logger.getLogger(ServiceHook.class);

    @Before
    public void setUp() throws Exception {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("chromedriver").getFile());
        file.setExecutable(true);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        //BROWSERS
//        System.setProperty("webdriver.chrome.driver", "/Users/msguner/Desktop/chromedriver");
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 2); //beforeValue : 0
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.addArguments("disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--start-fullscreen");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-proxy-server");
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        ThreadLocalDriver.setDriver(driver);
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        LOGGER.info("After..");

        try {
            if (scenario.isFailed()) {
                if (scenario.getSourceTagNames().isEmpty())
                    LOGGER.error("The Scenario: '" + scenario.getName() + "' FAILED");
                else
                    LOGGER.error("The Scenario: '" + scenario.getName() + "' with tags: " + scenario.getSourceTagNames() + " FAILED");

                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");

            } else {
                if (scenario.getSourceTagNames().isEmpty())
                    LOGGER.info("The Scenario: '" + scenario.getName() + "' PASSED");
                else
                    LOGGER.error("The Scenario: '" + scenario.getName() + "' with tags: " + scenario.getSourceTagNames() + " PASSED");
            }
        } finally {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        /*
        if (result.isSuccess()) {
            try {
                File imageFile = ((TakesScreenshot) ThreadLocalDriver.getDriver()).getScreenshotAs(OutputType.FILE);
                String failureImageFileName = result.getMethod().getMethodName()
                        + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";

                File failureImageFile = new File(System.getProperty("user.dir") + "//screenshots//" + failureImageFileName);
                FileUtils.copyFile(imageFile, failureImageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        */


        /*
        if (scenario.isFailed()) {
            try {
                File imageFile = ((TakesScreenshot) ThreadLocalDriver.getDriver()).getScreenshotAs(OutputType.FILE);
                String failureImageFileName = scenario.getName()
                        + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";

                System.out.println("user dir : " + System.getProperty("user.dir"));
                File failureImageFile = new File(System.getProperty("user.dir") + "//screenshots//" + failureImageFileName);
                FileUtils.copyFile(imageFile, failureImageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        */
    }
}