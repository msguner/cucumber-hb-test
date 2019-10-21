package pages;

import constants.LoginPageConstants;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String email) {
        waitElementAndSendKeys(LoginPageConstants.EMAIL, 5, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        waitElementAndSendKeys(LoginPageConstants.PASSWORD, 5, password);
        return this;
    }

    public HomePage clickLogIn() {
        waitAndClickElement(5, LoginPageConstants.LOGIN);
        return new HomePage(driver);
    }

    @Override
    public boolean isInitializePage() {
        return isExist(5, LoginPageConstants.LOGIN);
    }
}
