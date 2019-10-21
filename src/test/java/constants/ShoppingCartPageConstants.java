package constants;

import org.openqa.selenium.By;

public class ShoppingCartPageConstants {
    //    public static By DELETE_BUTTON = By.xpath("//a[@class='btn-delete' and text()='Sil']");
    public static By DELETE_BUTTON = By.xpath("//div[@class='utils']/*[text()='Sil']");
    public static By EMPTY_CART_CONTAINER = By.xpath("//div[@class='empty-cart-icon-container']");
    public static By PRODUCT_NAME = By.xpath("//h4[@class='product-name']");
    public static By PRODUCT_SELLER = By.xpath("//ul[@class='cart-item-list']//div[@class='product-detail']/div[@class='merchant']/a");
}
