package steps;

import helper.JSWaiter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import models.Product;
import org.testng.Assert;
import pages.*;
import utils.DataStore;
import utils.DataStoreFactory;

import java.util.ArrayList;

public class TestSteps extends BaseSteps {

    LoginPage loginP;
    HomePage homeP;
    HeaderPage headerP;
    SearchResultPage searchResultP;
    ProductDetailPage productDetailP;
    ShoppingCartPage shoppingCartP;

    public TestSteps() {
        loginP = new LoginPage(driver);
        headerP = new HeaderPage(driver);
        homeP = new HomePage(driver);
        searchResultP = new SearchResultPage(driver);
        productDetailP = new ProductDetailPage(driver);
        shoppingCartP = new ShoppingCartPage(driver);
    }

    @When("^User logs in using email as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void logIn(String email, String password) {
        loginP.enterEmail(email).enterPassword(password).clickLogIn();
    }

    @And("^Enter username \"([^\"]*)\"$")
    public void enterEmail(String email) {
        loginP.enterEmail(email);
    }

    @And("^Enter password \"([^\"]*)\"$")
    public void enterPassword(String password) {
        loginP.enterPassword(password);
    }

    @And("^Click log-in button$")
    public void clickLogInButton() {
        loginP.clickLogIn();
    }

    @And("User is at the login page of the application")
    public void userIsAtTheLoginPageOfTheApplication() {
        Assert.assertTrue(loginP.isInitializePage(), "The login page could not be displayed.");
    }

    @Given("Open login page from user process menu")
    public void openLoginPageFromUserProcessMenu() {
        new HeaderPage(driver).openUserProcessMenu().openLoginPage();
    }

    @And("Verifies that the user is on the home page")
    public void isUserInHomePage() {
        Assert.assertTrue(homeP.isInitializePage(), "The home page could not be displayed.");
    }

    @And("Verifies that the user has logged in")
    public void isTheUserLoggedIn() {
        Assert.assertTrue(headerP.isUserLoggedIn(), "User is not logged in");
    }

    @Given("Open hepsiburada.com website and wait for load scripts")
    public void openHepsiburadaAndWaitForLoadScripts() {
        driver.get("https://www.hepsiburada.com/");
        JSWaiter.waitUntilJSReady();
        JSWaiter.ajaxComplete();
        JSWaiter.waitUntilJQueryReady();
        JSWaiter.waitUntilAngularReady();
        JSWaiter.waitUntilAngular5Ready();
    }

    @And("Search product {string}")
    public void searchProduct(String product) {
        headerP.enterSearchText(product).clickSearch();
    }

    @And("Open first product")
    public void openFirstProduct() {
        searchResultP.openProduct(0);
    }

    @And("Add items to cart from different seller")
    public void addItemsToCartFromDifferentSeller() {
        DataStore dataStore = DataStoreFactory.getScenarioDataStore();
        ArrayList<Product> addedProducts = new ArrayList<>();
        dataStore.put("addedProducts", addedProducts);

        //Add first product to basket
        Product firstProduct = productDetailP.getProductInfo();
        addedProducts.add(firstProduct);
        productDetailP.addToBasket();

        //back to product
        waitSeconds(2);
        navigateToPreviousPage();

        //Add second product to basket
        productDetailP.openProductFromOtherSellers(0);
        Product secondProduct = productDetailP.getProductInfo();
        addedProducts.add(secondProduct);
        productDetailP.addToBasket();
    }

    @And("Empty shopping cart")
    public void emptyShoppingCart() {
        shoppingCartP.emptyShoppingCart();
    }

    @And("Open shopping cart")
    public void openShoppingCart() {
        headerP.openShoppingCart();
    }

    @And("Check added products from shopping cart")
    public void checkAddedProductsFromShoppingCart() {
        ArrayList<Product> addedProducts = (ArrayList<Product>) DataStoreFactory.getScenarioDataStore().get("addedProducts");
        ArrayList<Product> shoppingCartProducts = shoppingCartP.getAllProducts();

        //Direk bunun çalışması gerekiyor. Fakat ürünler eklendikten sonra başlığa renk ve kapasite vb ozellıkler eklenıyor
        // Bu kontrolu daha sonra saglarım. şimdilik contains ile kontrolu tamamlıyorum
//        Assert.assertTrue(addedProducts.equals(shoppingCartProducts), "Added products are not equal to shopping cart products");

        for (int i = 0; i < addedProducts.size(); i++) {
            String sCartProdName = shoppingCartProducts.get(i).getName();
            String sCartSeller = shoppingCartProducts.get(i).getSeller();
            String addedProdName = addedProducts.get(i).getName();
            String addedSeller = addedProducts.get(i).getSeller();

            Assert.assertTrue(sCartProdName.contains(addedProdName) && sCartSeller.equals(addedSeller),
                    "Added product are not equal to shopping cart product.");
        }
    }
}
