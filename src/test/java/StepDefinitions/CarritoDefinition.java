package StepDefinitions;

import Page.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CarritoDefinition {

    WebDriver hooks = Hooks.driver;
    MenuPage menu;
    UtilitariosPage utilitario;
    HomePage home;
    DatosTarjetaPage datos;
    FormularioPagoPage ingresaDatos;
    PaymentPage pago;
    String ruta = "src/test/resources/Data/dataCarrito.csv";

    BalancePage balance;


    public CarritoDefinition() {
        menu = new MenuPage(Hooks.driver);
        datos= new DatosTarjetaPage(Hooks.driver);
        utilitario= new UtilitariosPage(Hooks.driver);
        home= new HomePage(Hooks.driver);
        ingresaDatos= new FormularioPagoPage(Hooks.driver);
        pago = new PaymentPage(Hooks.driver);
        balance = new BalancePage(Hooks.driver);

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
        ingresaDatos.ingresarNumeroTarjeta();

    }

    @And("ingresa el numero de CVV")
    public void ingresaElNumeroDeCVV() {
        ingresaDatos.ingresarCVV();

    }

    @And("ingresa el mes y año de vencimiento")
    public void ingresaElMesYAñoDeVencimiento() {
        ingresaDatos.ingresarMes();
        ingresaDatos.ingresarAnio();

    }

    @And("dar click en comprar")
    public void darClickEnComprar() {
        ingresaDatos.clickBotonPagar();

    }

    @Then("visualiza un mensaje de compra exitosa")
    public void visualizaUnMensajeDeCompraExitosa() {
        pago.validarPagoExitoso();


    }

    @And("el numero de orden de compra")
    public void elNumeroDeOrdenDeCompra() {
        pago.CapturarOrden();

    }

    @And("da click en home")
    public void daClickEnHome() {
        pago.ClickHome();
        home.ValidarHome();

    }

    @When("el usuario obtiene los datos de la tarjeta")
    public void elUsuarioObtieneLosDatosDeLaTarjeta() {
        menu.ClickGenerarTarjeta();
        utilitario.cambiaVentana();
        datos.setCapturaTtarjeta();
        datos.setCapturarCVV();
        datos.setCapturarFecha();
        datos.setCapturarCredito();
        utilitario.cerrarVentana();

    }

    @And("el usuario ingresa los datos de la tarjeta y paga")
    public void elUsuarioIngresaLosDatosDeLaTarjetaYPaga() {
        ingresaDatos.ingresarNumeroTarjeta();
        ingresaDatos.ingresarCVV();
        ingresaDatos.ingresarMes();
        ingresaDatos.ingresarAnio();
        ingresaDatos.clickBotonPagar();

    }

    @Then("el usuario visualiza que la compra ha sido exitosa y ve el numero de orden")
    public void elUsuarioVisualizaQueLaCompraHaSidoExitosaYVeElNumeroDeOrden() {
        pago.validarPagoExitoso();
        pago.CapturarOrden();

    }

    @And("el usuario agrega un producto y da comprar")
    public void elUsuarioAgregaUnProductoYDaComprar() {
        home.CapturarPrecio();
        home.SeleccionarCantidad();
        home.ClickComprar();
    }

    @And("el usario agrega la cantidad de producto {string}")
    public void elUsarioAgregaLaCantidadDeProducto(String amount) {
        home.SeleccionarCantidad(amount);

    }

    @And("el usuario agrega la cantidad de producto")
    public void elUsuarioAgregaLaCantidadDeProducto(DataTable dataTable) {
        List<Map<String,String >> lista=dataTable.asMaps(String.class,String.class);
        for (int i=0; i<lista.size();i++){
            home.SeleccionarCantidad(lista.get(i).get("cantidad"));


        }
    }

    @And("el usuario agrega la cantidad de producto {string}")
    public void elUsuarioAgregaLaCantidadDeProducto(String cantidad) {
        home.SeleccionarCantidad(cantidad);

    }

    @And("el usuario agrega la cantidad desde archivo csv")
    public void elUsuarioAgregaLaCantidadDesdeArchivoCsv() throws IOException {

        BufferedReader leerArchivo= Files.newBufferedReader(Paths.get(ruta));
        CSVFormat formato = CSVFormat.DEFAULT.withHeader("cantidad")
                .withSkipHeaderRecord()
                .withTrim();
        Iterable<CSVRecord> registro = formato.parse(leerArchivo);

        for (CSVRecord fila:registro){
            home.SeleccionarCantidad(fila.get("cantidad"));

        }

    }

    @And("se da click en menu Check Credit Card Limit")
    public void seDaClickEnMenuCheckCreditCardLimit() {
        menu.ClickCheckBalance();
    }

    @And("se ingresa el numero de la tarjeta")
    public void seIngresaElNumeroDeLaTarjeta() {
        balance.ingresarNumeroTarjeta();

    }

    @And("dar click en enviar")
    public void darClickEnEnviar() {
        balance.ClickBotonEnviar();

    }

    @Then("se muestra la pagina con los datos")
    public void seMuestraLaPaginaConLosDatos() {
        balance.validarPaginaCreditCardBalance();


    }

    @And("validamos los datos de la tarjeta y el numero de orden")
    public void validamosLosDatosDeLaTarjetaYElNumeroDeOrden() {
        balance.validarNumeroTarjeta();
        balance.validarMes();
        balance.validarAnio();
        balance.validarCCV();
        balance.validarNumeroOrden();
       menu.ClickCheckHome();


    }
}
