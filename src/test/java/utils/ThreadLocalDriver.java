package utils;

import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public synchronized static WebDriver getDriver() {
        return tlDriver.get();
    }

    public synchronized static void setDriver(WebDriver driver) {
        tlDriver.set(driver);
    }
}