package constants;

import org.openqa.selenium.By;

public class ProductDetailPageConstants {
    public static final By OTHER_SELLERS_PRODUCT = By.xpath("//div[@class='marketplace-list']//tr");
    public static By PRODUCT_NAME = By.id("product-name");
    public static By PRODUCT_SELLER = By.xpath("//span[@class='seller']/span[text()!='Satıcı:']");
    public static By ADD_TO_CART = By.id("addToCart");
}
