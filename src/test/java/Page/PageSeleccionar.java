package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageSeleccionar {

    //Instanciar Wait y Driver

    private WebDriver driver;
    private WebDriverWait wait;

    public PageSeleccionar(WebDriver d) {
        driver =d;
        wait= new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    //declarar localizadores que vamos a usar

    @FindBy(id = "department") private WebElement departamento;
    @FindBy(id = "city") private WebElement ciudad;
    @FindBy(id = "commands") private WebElement comandos;

    // crear metodos

    public void seleccionarDepartamento(){

        new Select(departamento).selectByVisibleText("ICA");
    }
    public void seleccionarDepartamento(String department){

        new Select(departamento).selectByVisibleText(department);
    }



    public void seleccionarCiudad(){

        new Select(ciudad).selectByVisibleText("ICA");
    }

    public void seleccionarCiudad( String city){

        new Select(ciudad).selectByVisibleText(city);
    }



    public void seleccionarComando(){

        new Select(comandos).selectByVisibleText("WebElement Commands");
    }

    public void seleccionarComando(String comand){

        new Select(comandos).selectByVisibleText(comand);
    }


}
