package pages;

import constants.SearchResultPageConstants;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailPage openProduct(int index) {
        waitAndClickElement(5, SearchResultPageConstants.PRODUCT_TITLE, index);
        return new ProductDetailPage(driver);
    }

    @Override
    public boolean isInitializePage() {
        return isExist(5, SearchResultPageConstants.SEARCH_DIV);
    }
}
