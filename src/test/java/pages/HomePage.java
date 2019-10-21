package pages;

import constants.HomePageConstants;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitializePage() {
        return isExist(3, HomePageConstants.HEROUSEL_CAROUSEL);
    }
}
