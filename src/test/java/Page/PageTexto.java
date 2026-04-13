package Page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTexto {

    //declarar variables Wait y Driver

   private WebDriver driver;
   private WebDriverWait wait;

   // declaramos el constructor


    public PageTexto(WebDriver d) {
        driver =d;
        wait= new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }


    protected String Snombre;
    protected String Sapellido;
    //declarar localizadores que vamos a usar

    @FindBy (id = "nombre") private WebElement name;
    @FindBy (id = "apellido") private WebElement apellido;
    @FindBy (id = "email") private WebElement correo;
    @FindBy (name = "mobile") private WebElement telefono;
    @FindBy (xpath = "//*[contains(text(),'Información Personal')]") private WebElement infoModal;
    @FindBy (id = "nombreModal") private WebElement nombreModal;
    @FindBy (id = "apellidoModal") private WebElement apellidoModal;

    // crear metodos

    public void validarDatos(){
        wait.until(ExpectedConditions.visibilityOf(nombreModal));
        nombreModal.isDisplayed();
        apellidoModal.isDisplayed();
    }

    public void ingresarNombre(){

        name.sendKeys("Fernando Roger");


    }

    public void ingresarNombre(String nombre){

        name.sendKeys(nombre);
        Snombre=nombre;

    }

    public void ingresarApellido(){

        apellido.sendKeys("Cuadros Lopez");
    }

    public void ingresarApellido(String lastName){

        apellido.sendKeys(lastName);
        Sapellido=lastName;
    }



    public void ingresarTelefono(){

        telefono.sendKeys("9876543567");
    }

    public void ingresarTelefono(String phone){

        telefono.sendKeys(phone);
    }

    public void ingresarCorreo(){

        correo.sendKeys("Fernando@test.com");
    }

    public void ingresarCorreo(String email){

        correo.sendKeys(email);
    }

    public void mostrarPopup(){
        wait.until(ExpectedConditions.visibilityOf(infoModal));
        infoModal.isDisplayed();
    }

    public void nombreModal(){

        Assert.assertEquals(name.getAttribute("value"), nombreModal.getText());
    }

    public void apellidoModal(){

        Assert.assertEquals(apellido.getAttribute("value"), apellidoModal.getText());
    }

    public void validarNombre(){
        wait.until(ExpectedConditions.visibilityOf(nombreModal));
        Assert.assertEquals(nombreModal.getText(), Snombre);
        System.out.println("el nombre actual es: " + nombreModal.getText());
        System.out.println("el nombre esperado es: " + Snombre);
    }

    public void validarApellido(){
        wait.until(ExpectedConditions.visibilityOf(apellidoModal));
        Assert.assertEquals(apellidoModal.getText(),Sapellido);
        System.out.println("el apellido actual es: " + apellidoModal.getText());
        System.out.println("el apellido esperado es: " + Sapellido);

    }

    public void validarNombre(String nombre){
        wait.until(ExpectedConditions.visibilityOf(nombreModal));
        Assert.assertEquals(nombreModal.getText(), nombre);
        System.out.println("el nombre actual es: " + nombreModal.getText());
        System.out.println("el nombre esperado es: " + Snombre);
    }

    public void validarApellido(String lastName){
        wait.until(ExpectedConditions.visibilityOf(apellidoModal));
        Assert.assertEquals(apellidoModal.getText(),lastName);
        System.out.println("el apellido actual es: " + apellidoModal.getText());
        System.out.println("el apellido esperado es: " + Sapellido);
    }

}
