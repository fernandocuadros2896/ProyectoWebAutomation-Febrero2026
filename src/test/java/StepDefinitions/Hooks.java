package StepDefinitions;

import Support.DriverFactory;
import Support.FailureContext;
import Support.FailureWebDriverListener;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    private static final Path SCREENSHOTS_DIR = Paths.get("target", "screenshots");
    private static final Duration FOCUS_WAIT = Duration.ofSeconds(2);
    private static final int TOP_OFFSET_PX = 120;

    private void scrollElementForEvidence(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Posiciona el elemento cerca del tope para que su contexto inferior sea visible.
        js.executeScript(
                "const rect=arguments[0].getBoundingClientRect();" +
                        "const y=rect.top + window.pageYOffset - arguments[1];" +
                        "window.scrollTo(0, Math.max(y, 0));",
                element,
                TOP_OFFSET_PX
        );
    }

    private boolean focusOnCardDataFallback(WebDriver driver, Scenario scenario) {
        try {
            WebElement expDate = driver.findElement(By.xpath("//h4[3]"));
            scrollElementForEvidence(driver, expDate);
            scenario.log("Fallback aplicado: screenshot enfocado en la seccion de datos de tarjeta.");
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String sanitizeFileName(String value) {
        return value.replaceAll("[^a-zA-Z0-9._-]", "_");
    }

    private void saveScreenshotToDisk(byte[] screenshot, Scenario scenario) throws IOException {
        Files.createDirectories(SCREENSHOTS_DIR);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        String scenarioName = sanitizeFileName(scenario.getName());
        String fileName = timestamp + "_" + scenarioName + ".png";

        Path screenshotPath = SCREENSHOTS_DIR.resolve(fileName);
        Files.write(screenshotPath, screenshot);
        scenario.log("Screenshot guardado en: " + screenshotPath.toAbsolutePath());
    }

    private boolean focusOnFailedElement(WebDriver driver, Scenario scenario) {
        try {
            By failedLocator = FailureContext.getLastLocator();

            if (failedLocator != null) {
                WebDriverWait wait = new WebDriverWait(driver, FOCUS_WAIT);
                WebElement failedElement = wait.until(d -> d.findElement(failedLocator));
                scenario.log("Elemento relocalizado para screenshot: " + failedLocator);
                scrollElementForEvidence(driver, failedElement);
                scenario.log("Screenshot enfocado en el locator del fallo.");
                return true;
            }

            WebElement failedElement = null;
            if (failedElement == null) {
                failedElement = FailureContext.getLastElement();
            }

            if (failedElement == null) {
                boolean focusedOnCardData = focusOnCardDataFallback(driver, scenario);
                if (!focusedOnCardData) {
                    scenario.log("No hay elemento registrado en el fallo; se usara viewport actual.");
                }
                return focusedOnCardData;
            }

            scrollElementForEvidence(driver, failedElement);
            scenario.log("Screenshot enfocado en el ultimo elemento disponible.");
            return true;
        } catch (WebDriverException e) {
            scenario.log("No se pudo enfocar el elemento de fallo: " + e.getMessage());
            return focusOnCardDataFallback(driver, scenario);
        }
    }

    private void attachFailureScreenshot(WebDriver driver, Scenario scenario) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Da tiempo a la UI para estabilizarse tras la excepcion antes del enfoque.
            Thread.sleep(500);

            boolean focusedOnFailedElement = focusOnFailedElement(driver, scenario);
            if (!focusedOnFailedElement) {
                Number scrollX = (Number) js.executeScript("return window.pageXOffset || 0;");
                Number scrollY = (Number) js.executeScript("return window.pageYOffset || 0;");
                js.executeScript("window.scrollTo(arguments[0], arguments[1]);", scrollX, scrollY);
            }

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            saveScreenshotToDisk(screenshot, scenario);
            scenario.attach(screenshot, "image/png", focusedOnFailedElement ? "Failed-element" : "Failed-viewport");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            scenario.log("Interrupcion durante captura de screenshot: " + e.getMessage());
        } catch (WebDriverException | IOException e) {
            scenario.log("No se pudo guardar/adjuntar screenshot de fallo: " + e.getMessage());
        }
    }

    @Before(order = 0)
    public void setUp() {
        FailureContext.clear();

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "true")
        );

        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver baseDriver = new ChromeDriver(options);
        WebDriverListener listener = new FailureWebDriverListener();
        WebDriver driver = new EventFiringDecorator<>(listener).decorate(baseDriver);

        DriverFactory.setDriver(driver);
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {

            if (scenario.isFailed()) {
                attachFailureScreenshot(driver, scenario);
            }

            DriverFactory.quitDriver();
            FailureContext.clear();
        }
    }
}
