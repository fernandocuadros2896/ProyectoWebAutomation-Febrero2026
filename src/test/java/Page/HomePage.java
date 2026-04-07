package Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    //declarar variables driver y wait para usarla en toda la clase.
    private WebDriver driver;
    private WebDriverWait wait;
    protected String precio;

    //declara el constructor

    public HomePage(WebDriver d) {
        //instanciar el driver y el wait
        driver = d;
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //declar localizadores
    @FindBy(tagName = "h3") private WebElement precioProducto;
    @FindBy(name = "quantity") private WebElement selectCantidad;
    @FindBy(xpath = "//*[@type=\"submit\"]") private WebElement botonCompra;

    //crear metodos para utilizar
    public void CapturarPrecio() {
        //capturar el precio del producto y quedarnos solo con los numeros
        precio = precioProducto.getText();
        precio = precio.replaceAll("\\D+", "");
        System.out.println("El precio del producto es: " + precio);

    }

    public void SeleccionarCantidad() {

        new Select(selectCantidad).selectByVisibleText("2");
    }

    public void ClickComprar() {

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonCompra);
    }
}
