package StepDefinitions;

import Page.DatosTarjetaPage;
import Page.HomePage;
import Page.MenuPage;
import Page.UtilitariosPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class CarritoDefinition {

    WebDriver hooks = Hooks.driver;
    MenuPage menu;
    UtilitariosPage utilitario;
    HomePage home;
    DatosTarjetaPage datos;

    public CarritoDefinition() {
        menu = new MenuPage(Hooks.driver);
        datos= new DatosTarjetaPage(Hooks.driver);
        utilitario= new UtilitariosPage(Hooks.driver);
        home= new HomePage(Hooks.driver);

    }

    @Given("el usuario ingresa a la pagina de productos")
    public void elUsuarioIngresaALaPaginaDeProductos() {
        Hooks.driver.get("https://demo.guru99.com/payment-gateway/index.php");

    }

    @When("el usuario obtiene el numero de tarjeta")
    public void elUsuarioObtieneElNumeroDeTarjeta() {
    menu.ClickGenerarTarjeta();
    utilitario.cambiaVentana();
    datos.setCapturaTtarjeta();

    }

    @And("el usuario obtiene el numero de CVV")
    public void elUsuarioObtieneElNumeroDeCVV() {
    datos.setCapturarCVV();

    }

    @And("el mes y año de vencimiento")
    public void elMesYAñoDeVencimiento() {
        datos.setCapturarFecha();
        datos.setCapturarCredito();
        utilitario.cerrarVentana();


    }

    @And("el usario agrega un producto")
    public void elUsarioAgregaUnProducto() {
        home.CapturarPrecio();
        home.SeleccionarCantidad();

    }

    @And("da click en comprar")
    public void daClickEnComprar() {
        home.ClickComprar();

    }

    @And("ingresa el numero de tarjeta")
    public void ingresaElNumeroDeTarjeta() {

    }

    @And("ingresa el numero de CVV")
    public void ingresaElNumeroDeCVV() {

    }

    @And("ingresa el mes y año de vencimiento")
    public void ingresaElMesYAñoDeVencimiento() {

    }

    @And("dar click en comprar")
    public void darClickEnComprar() {

    }

    @Then("visualiza un mensaje de compra exitosa")
    public void visualizaUnMensajeDeCompraExitosa() {

    }

    @And("el numero de orden de compra")
    public void elNumeroDeOrdenDeCompra() {

    }

    @And("da click en home")
    public void daClickEnHome() {

    }
}
