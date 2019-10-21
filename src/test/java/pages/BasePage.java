package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {
    protected static WebDriver driver;
    private static WebDriverWait wait;
    Actions actions;
    private static final int DEFAULT_WAIT = 10;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT, 1000);
        actions = new Actions(driver);
    }

    public abstract boolean isInitializePage();

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getText(int seconds, By elementBy) {
        return waitForElement(seconds, elementBy).getText();
    }

    public WebElement getElement(By byElement) {
        return driver.findElement(byElement);
    }

    public WebElement waitForElement(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    public List<WebElement> waitForElements(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
    }

    public void waitAndClickElement(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(elementBy)).click();
        System.out.println("Clicked : " + elementBy.toString());
    }

    public void waitAndClickElement(int seconds, By elementBy, int index) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy)).get(index).click();
        System.out.println("Clicked[" + index + "] : " + elementBy.toString());
    }

    public void waitElementAndSendKeys(By byElement, int Seconds, String text) {
        WebElement element = waitForElement(Seconds, byElement);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void waiterWithAssertion(int seconds, By elementBy, String name) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
        } catch (Exception e) {
            Assert.assertTrue(name + " bulunamadı!", false);
        }
    }

    //elementin varlığı kontrol edilir
    public boolean isExist(int seconds, By by) {
        try {
            waitForElement(seconds, by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Belirtilen elemente doğru sayfa scroll edilir
    public void scrollToElement(By byElement) {
        WebElement element = getElement(byElement);
        actions.moveToElement(element);
        actions.perform();
    }

    public void clickElementWithJsExecuter(int seconds, By elementBy) {
        WebElement element = waitForElement(seconds, elementBy);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public boolean isDisplayedWithJS(By byEl) {
        WebElement el = getElement(byEl);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return (Boolean) jse.executeScript("function isVisible(elem) {\n" +
                "    if (!(elem instanceof Element)) throw Error('DomUtil: elem is not an element.');\n" +
                "    const style = getComputedStyle(elem);\n" +
                "    if (style.display === 'none') return false;\n" +
                "    if (style.visibility !== 'visible') return false;\n" +
                "    if (style.opacity < 0.1) return false;\n" +
                "    if (elem.offsetWidth + elem.offsetHeight + elem.getBoundingClientRect().height +\n" +
                "        elem.getBoundingClientRect().width === 0) {\n" +
                "        return false;\n" +
                "    }\n" +
                "    const elemCenter   = {\n" +
                "        x: elem.getBoundingClientRect().left + elem.offsetWidth / 2,\n" +
                "        y: elem.getBoundingClientRect().top + elem.offsetHeight / 2\n" +
                "    };\n" +
                "    if (elemCenter.x < 0) return false;\n" +
                "    if (elemCenter.x > (document.documentElement.clientWidth || window.innerWidth)) return false;\n" +
                "    if (elemCenter.y < 0) return false;\n" +
                "    if (elemCenter.y > (document.documentElement.clientHeight || window.innerHeight)) return false;\n" +
                "    let pointContainer = document.elementFromPoint(elemCenter.x, elemCenter.y);\n" +
                "    do {\n" +
                "        if (pointContainer === elem) return true;\n" +
                "    } while (pointContainer = pointContainer.parentNode);\n" +
                "    return false;\n" +
                "}\n" +
                "return isVisible(arguments[0])", el);
    }

    public void clickToElementWithJs(By byEl) {
        WebElement el = getElement(byEl);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", el);
    }
}
