package Page;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BalancePage {
    //declarar variables driver y wait para usarla en toda la clase.
    private WebDriver driver;
    private WebDriverWait wait;

    public BalancePage(WebDriver d) {
        //instanciar el driver y el wait
        driver = d;
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }



    //declarar localizadores
    @FindBy(id = "card_nmuber") private WebElement numeroTarjeta;
    @FindBy(xpath = "//*[@type=\"submit\"]") private WebElement botonEnviar;
    @FindBy(xpath = "//h4") private WebElement creditCartBalance;
    @FindBy(xpath = "//td[1]/b") private WebElement validarNumeroTarjeta;
    @FindBy(xpath = "//td[3]/b") private WebElement validarMes;
    @FindBy(xpath = "//td[4]/b") private WebElement validarAnio;
    @FindBy(xpath = "//td[5]/b") private WebElement validarCCV;
    @FindBy(xpath = "//td[6]/b") private WebElement validarNumeroOrden;



    //Declarar metodos a utilizar

    public void ingresarNumeroTarjeta(){
        numeroTarjeta.sendKeys(DatosTarjetaPage.tarjeta);

    }

    public void ClickBotonEnviar() {

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonEnviar);
    }

    public void validarPaginaCreditCardBalance(){
        wait.until(ExpectedConditions.visibilityOf(creditCartBalance));
        creditCartBalance.isDisplayed();
        System.out.println("el nomnbre de la pagina actual es: " + creditCartBalance);
    }




    public void validarNumeroTarjeta(){
        wait.until(ExpectedConditions.visibilityOf(validarNumeroTarjeta));
        Assert.assertEquals(DatosTarjetaPage.tarjeta, validarNumeroTarjeta.getText());
        System.out.println("el nombre actual es: " + validarNumeroTarjeta.getText());
        System.out.println("el nombre esperado es: " + DatosTarjetaPage.tarjeta);
    }

    public void validarMes(){
        wait.until(ExpectedConditions.visibilityOf(validarMes));
        String mesActual = normalizarMes(validarMes.getText());
        String mesEsperado = normalizarMes(DatosTarjetaPage.mes);
        Assert.assertEquals(mesEsperado, mesActual);
        System.out.println("el nombre actual es: " + mesActual);
        System.out.println("el nombre esperado es: " + mesEsperado);
    }

    public void validarAnio(){
        wait.until(ExpectedConditions.visibilityOf(validarAnio));
        Assert.assertEquals(DatosTarjetaPage.anio, validarAnio.getText());
        System.out.println("el nombre actual es: " + validarAnio.getText());
        System.out.println("el nombre esperado es: " + DatosTarjetaPage.anio);
    }
    public void validarCCV(){
        wait.until(ExpectedConditions.visibilityOf(validarCCV));
        Assert.assertEquals(DatosTarjetaPage.cvv, validarCCV.getText());
        System.out.println("el nombre actual es: " + validarCCV.getText());
        System.out.println("el nombre esperado es: " + DatosTarjetaPage.cvv);
    }

    public void validarNumeroOrden(){
        wait.until(ExpectedConditions.visibilityOf(validarNumeroOrden));
        String numeroOrdenActual = normalizarNumero(validarNumeroOrden.getText());
        String numeroOrdenEsperado = normalizarNumero(PaymentPage.numeroOrden);
        Assert.assertEquals(numeroOrdenEsperado, numeroOrdenActual);
        System.out.println("el nombre actual es: " + numeroOrdenActual);
        System.out.println("el nombre esperado es: " + numeroOrdenEsperado);
    }

    private String normalizarMes(String valorMes) {
        String soloDigitos = valorMes.replaceAll("\\D+", "");
        return String.format("%02d", Integer.parseInt(soloDigitos));
    }

    private String normalizarNumero(String valor) {
        String soloDigitos = valor.replaceAll("\\D+", "");
        return soloDigitos.replaceFirst("^0+(?!$)", "");
    }




}
