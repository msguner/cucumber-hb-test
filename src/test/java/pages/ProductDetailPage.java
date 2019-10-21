package pages;

import constants.ProductDetailPageConstants;
import models.Product;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitializePage() {
        return false;
    }

    public String getProductName() {
        return getText(5, ProductDetailPageConstants.PRODUCT_NAME);
    }

    public String getProductSeller() {
        return getText(5, ProductDetailPageConstants.PRODUCT_SELLER);
    }

    public Product getProductInfo() {
        return new Product(getProductName(), getProductSeller());
    }

    public ShoppingCartPage addToBasket() {
        waitAndClickElement(5, ProductDetailPageConstants.ADD_TO_CART);
        return new ShoppingCartPage(driver);
    }

    public ProductDetailPage openProductFromOtherSellers(int index) {
        waitAndClickElement(5, ProductDetailPageConstants.OTHER_SELLERS_PRODUCT, index);
        return this;
    }
}
