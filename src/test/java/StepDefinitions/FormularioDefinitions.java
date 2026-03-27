package StepDefinitions;

import Page.PageBotones;
import Page.PageClick;
import Page.PageSeleccionar;
import Page.PageTexto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class FormularioDefinitions {

    WebDriver hooks = Hooks.driver;
    //llamamos la clase a utilizar
    PageTexto texto;
    PageClick click;
    PageSeleccionar seleccionar;
    PageBotones boton;


    // Iniciar variables en el constructor
    public FormularioDefinitions() {
        // instaciar las clases
        texto = new PageTexto(Hooks.driver);
        click = new PageClick(Hooks.driver);
        seleccionar = new PageSeleccionar(Hooks.driver);
        boton = new PageBotones(Hooks.driver);


    }

    //Steps
    @Given("que ingreso a la web de Novus Technology")
    public void queIngresoALaWebDeNovusTechnology() {

        Hooks.driver.get("https://novustechnology.pe/practice-form/");
    }

    @When("Ingreso el nombre")
    public void ingresoElNombre() {
        texto.ingresarNombre();
    }

    @And("Ingreso el apellido")
    public void ingresoElApellido() {
        texto.ingresarApellido();

    }

    @And("ingreso el telefono")
    public void ingresoElTelefono() {

        texto.ingresarTelefono();
    }

    @And("ingreso el correo")
    public void ingresoElCorreo() {

        texto.ingresarCorreo();
    }

    @And("ingreso el pasatiempo")
    public void ingresoElPasatiempo() {

        click.clickPasatiempo();
    }

    @And("ingreso el genero")
    public void ingresoElGenero() {
        click.clickGenero();

    }

    @And("ingreso un documento")
    public void ingresoUnDocumento() {
        click.CargarDocumento();

    }


    @And("selecciono el departamento")
    public void seleccionoElDepartamento() {

        seleccionar.seleccionarDepartamento();
    }

    @And("selecciono la ciudad")
    public void seleccionoLaCiudad() {

        seleccionar.seleccionarCiudad();

    }

    @And("selecciono el tipo de comando")
    public void seleccionoElTipoDeComando() {

        seleccionar.seleccionarComando();

    }

    @And("doy click en enviar")
    public void doyClickEnEnviar() {

        boton.botonEnviar();
    }

    @Then("se muestra un pop up con el mensaje")
    public void seMuestraUnPopUpConElMensaje() {
        texto.mostrarPopup();
    }

    @And("doy click en close")
    public void doyClickEnClose() {
        boton.botonCerrar();
    }

    @And("valido que el nombre sea el mismo que se ingreso en el formulario")
    public void validoQueElNombreSeaElMismoQueSeIngresoEnElFormulario() {
       texto.nombreModal();
    }

    @And("valido que el apellido sea el mismo que se ingreso en el formulario")
    public void validoQueElApellidoSeaElMismoQueSeIngresoEnElFormulario() {
texto.apellidoModal();
    }

    @And("envio el formulario")
    public void envioElFormulario() {
        boton.botonEnviar();
    }

    @Then("se muestra un mensaje con los datos ingresados")
    public void seMuestraUnMensajeConLosDatosIngresados() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("ingreso los datos del formulario")
    public void ingresoLosDatosDelFormulario() {
       texto.ingresarNombre();
       texto.ingresarApellido();
       click.clickPasatiempo();
       click.clickGenero();
       texto.ingresarTelefono();
       texto.ingresarCorreo();
       seleccionar.seleccionarDepartamento();
       seleccionar.seleccionarCiudad();
       seleccionar.seleccionarComando();
       click.CargarDocumento();

    }

    @When("Ingreso el campo nombre {string}")
    public void ingresoElCampoNombre(String nombre) {
      texto.ingresarNombre(nombre);
    }

    @And("Ingreso el campo apellido {string}")
    public void ingresoElCampoApellido(String apellido) {
       texto.ingresarApellido(apellido);
    }

    @And("ingreso el pasatiempo {string}, {string}")
    public void ingresoElPasatiempo(String arg0, String arg1) {

    }

    @And("ingreso el pasatiempo {string}")
    public void ingresoElPasatiempo(String pasatiempo) {
        click.clickPasatiempo(pasatiempo);
    }

    @And("selecciono el genero {string}")
    public void seleccionoElGenero(String genero) {
       click.clickGenero(genero);
    }

    @And("ingreso el telefono {string}")
    public void ingresoElTelefono(String telefono) {
        texto.ingresarTelefono(telefono);
    }

    @And("ingreso el correo {string}")
    public void ingresoElCorreo(String correo) {
       texto.ingresarCorreo(correo);
    }

    @And("selecciono el departamento {string}")
    public void seleccionoElDepartamento(String departamento) {
       seleccionar.seleccionarDepartamento(departamento);
    }

    @And("selecciono la ciudad {string}")
    public void seleccionoLaCiudad(String ciudad) {
        seleccionar.seleccionarCiudad(ciudad);

    }

    @And("selecciono el tipo de comando {string}")
    public void seleccionoElTipoDeComando(String comando) {
       seleccionar.seleccionarComando(comando);
    }

    @And("ingreso un documento desde la ruta {string}")
    public void ingresoUnDocumentoDesdeLaRuta(String documento) {
        click.CargarDocumento(documento);
    }

    @And("valido que el nombre sea el mismo que se ingreso al inicio")
    public void validoQueElNombreSeaElMismoQueSeIngresoAlInicio() {
      texto.validarNombre();
    }

    @And("valido que el apellido sea el mismo que se ingreso al inicio")
    public void validoQueElApellidoSeaElMismoQueSeIngresoAlInicio() {
       texto.validarApellido();
    }

    @When("Ingreso datos del usuario")
    public void ingresoDatosDelUsuario(DataTable dataTable) {
        List<Map<String,String >> lista=dataTable.asMaps(String.class,String.class);
        for (int i=0; i<lista.size();i++){
            texto.ingresarNombre(lista.get(i).get("nombre"));
            texto.ingresarApellido(lista.get(i).get("apellido"));
            click.clickPasatiempo(lista.get(i).get("pasatiempo"));
            click.clickGenero(lista.get(i).get("genero"));
            texto.ingresarTelefono(lista.get(i).get("telefono"));
            texto.ingresarCorreo(lista.get(i).get("correo"));
            seleccionar.seleccionarDepartamento(lista.get(i).get("departamento"));
            seleccionar.seleccionarCiudad(lista.get(i).get("ciudad"));
            seleccionar.seleccionarComando(lista.get(i).get("comando"));
            click.CargarDocumento(lista.get(i).get("ruta"));
        }
    }

    @And("valido los datos")
    public void validoLosDatos(DataTable dataTable) {
        List<Map<String,String >> lista=dataTable.asMaps(String.class,String.class);
        for (int i=0; i<lista.size();i++){
            texto.validarNombre(lista.get(i).get("nombre"));
            texto.validarApellido(lista.get(i).get("apellido"));

        }

    }
}
