package pages;

import constants.ShoppingCartPageConstants;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    //TODO selenium isExist metodunda bir sorun var. Biraz gecikme yaşanıyor. sürümden kaynaklı olabilir. İncelenecek!
    public ShoppingCartPage emptyShoppingCart() {
        while (isExist(2, ShoppingCartPageConstants.DELETE_BUTTON)) {
            clickToElementWithJs(ShoppingCartPageConstants.DELETE_BUTTON);
            waitSeconds(1);
        }
        return this;
    }

    public ArrayList<Product> getAllProducts() {
        List<WebElement> productNames = waitForElements(5, ShoppingCartPageConstants.PRODUCT_NAME);
        List<WebElement> productSellers = waitForElements(5, ShoppingCartPageConstants.PRODUCT_SELLER);
        ArrayList<Product> productsList = new ArrayList<>();

        for (int i = 0; i < productNames.size(); i++) {
            productsList.add(new Product(productNames.get(i).getText(), productSellers.get(i).getText()));
        }
        return productsList;
    }

    @Override
    public boolean isInitializePage() {
        return false;
    }
}
