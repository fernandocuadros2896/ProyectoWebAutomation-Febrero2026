package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageClick {
    //Instanciar Wait y Driver

    private WebDriver driver;
    private WebDriverWait wait;

    public PageClick(WebDriver d) {
        driver =d;
        wait= new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //declarar localizadores que vamos a usar

    @FindBy(id = "preloader") private WebElement loading;
    @FindBy(id = "music") private WebElement musica;
    @FindBy(id = "male") private WebElement masculino;

    @FindBy(id = "sports") private WebElement deporte;
    @FindBy(id = "reading") private WebElement lectura;
    @FindBy(id = "female") private WebElement femenino;
    @FindBy(id = "other") private WebElement otro;

    //CARGAR ARCHIVO
    @FindBy(id = "picture") private WebElement cargarDocumento;

    // crear metodos

    public void clickPasatiempo(){

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", musica);
    }

    public void clickPasatiempo(String pasatiempo){
        wait.until(ExpectedConditions.invisibilityOf(loading));
        if (pasatiempo.equals("deporte")) {
            deporte.click();
        } else if (pasatiempo.equals("lectura")) {

            lectura.click();
        } else {
            musica.click();
        }
    }
    public void clickGenero(){

        wait.until(ExpectedConditions.invisibilityOf(loading));
        masculino.click();
    }
    public void clickGenero(String genero){

        wait.until(ExpectedConditions.invisibilityOf(loading));

        if (genero.equals("masculino")){
            masculino.click();
        } else if (genero.equals("femenino")) {
            femenino.click();
        } else {
            otro.click();
        }
    }

    public void CargarDocumento() {
        cargarDocumento.sendKeys("C:\\Users\\FERNANDO\\Downloads\\prueba.pdf");
    }

    public void CargarDocumento(String document) {
        cargarDocumento.sendKeys(document);
    }

}
