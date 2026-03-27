package Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBotones {

    //Instanciar Wait y Driver

    private WebDriver driver;
    private WebDriverWait wait;

    public PageBotones(WebDriver d) {
        driver =d;
        wait= new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //declarar localizadores que vamos a usar

    @FindBy(xpath = "//button[text()='Enviar']") private WebElement enviar;
    @FindBy(id = "liveToastBtn") private WebElement toast;
    @FindBy(id= "mostrarAlerta") private WebElement alerta;
    @FindBy(xpath = "//*[@id=\"infoModal\"]/div/div/div[3]/button") private WebElement cerrar;

    // crear metodos

    public void botonEnviar(){

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", enviar);
    }

    public void botonCerrar(){

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cerrar);
    }

}
