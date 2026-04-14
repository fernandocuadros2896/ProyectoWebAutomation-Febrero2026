package Support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FailureContext {
    private static final ThreadLocal<WebElement> lastElement = new ThreadLocal<>();
    private static final ThreadLocal<By> lastLocator = new ThreadLocal<>();

    public static void setLastElement(WebElement element) {
        lastElement.set(element);
    }

    public static WebElement getLastElement() {
        return lastElement.get();
    }

    public static void setLastLocator(By locator) {
        lastLocator.set(locator);
    }

    public static By getLastLocator() {
        return lastLocator.get();
    }

    public static void clear() {
        lastElement.remove();
        lastLocator.remove();
    }
}
