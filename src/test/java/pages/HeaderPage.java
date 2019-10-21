package pages;

import constants.HeaderPageConstants;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public HeaderPage openUserProcessMenu() {
        scrollToElement(HeaderPageConstants.HEADER_LOGIN);
        return this;
    }

    public LoginPage openLoginPage() {
        waitAndClickElement(5, HeaderPageConstants.USER_PROCESS_MENU_LOGIN);
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        return getElement(HeaderPageConstants.LOGIN_USER_NAME).isDisplayed();
    }

    public HeaderPage enterSearchText(String text) {
        waitElementAndSendKeys(HeaderPageConstants.SEARCH_INPUT, 5, text);
        return this;
    }

    public SearchResultPage clickSearch() {
        waitAndClickElement(5, HeaderPageConstants.SEARCH_BTN);
        return new SearchResultPage(driver);
    }

    @Override
    public boolean isInitializePage() {
        return false;
    }

    public ShoppingCartPage openShoppingCart() {
        waitAndClickElement(5, HeaderPageConstants.SHOPPING_CART);
        return new ShoppingCartPage(driver);
    }
}
