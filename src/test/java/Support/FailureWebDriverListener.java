package Support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class FailureWebDriverListener implements WebDriverListener {
    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        FailureContext.setLastLocator(locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        FailureContext.setLastLocator(locator);
        FailureContext.setLastElement(result);
    }

    @Override
    public void beforeClick(WebElement element) {
        FailureContext.setLastElement(element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        FailureContext.setLastElement(element);
    }

    @Override
    public void beforeClear(WebElement element) {
        FailureContext.setLastElement(element);
    }

    @Override
    public void beforeSubmit(WebElement element) {
        FailureContext.setLastElement(element);
    }
}
