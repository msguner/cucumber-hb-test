package constants;

import org.openqa.selenium.By;

public class HeaderPageConstants {
    public static By HEADER_LOGIN = By.xpath("//div[@id='myAccount' and @class='icon-view-account']");
    public static By USER_PROCESS_MENU_LOGIN = By.xpath("//div[@class='usersProcess']/ul/li/a[@id='login']");
    public static By SEARCH_INPUT = By.id("productSearch");
    public static By SEARCH_BTN = By.id("buttonProductSearch");
    public static By LOGIN_USER_NAME = By.xpath("//a[@class='login user-name']");
    public static By SHOPPING_CART = By.id("shoppingCart");
}
