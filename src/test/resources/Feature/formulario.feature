Feature: Registrar Formulario Novus Technology

  @Prueba1
  Scenario: Se registra el formulario de Novus Technology con datos válidos
    Given que ingreso a la web de Novus Technology
    When Ingreso el nombre
    And Ingreso el apellido
    And ingreso el pasatiempo
    And ingreso el genero
    And ingreso el telefono
    And ingreso el correo
    And selecciono el departamento
    And selecciono la ciudad
    And selecciono el tipo de comando
    And ingreso un documento
    And doy click en enviar
    Then se muestra un pop up con el mensaje
    And valido que el nombre sea el mismo que se ingreso en el formulario
    And valido que el apellido sea el mismo que se ingreso en el formulario
    And doy click en close

  @Prueba3
  Scenario: registro exitoso en el formulario Novus
    Given que ingreso a la web de Novus Technology
    When ingreso los datos del formulario
    And envio el formulario
    Then se muestra un pop up con el mensaje
    And doy click en close


  @Prueba2
  Scenario: Validar datos desde el escenario en el formulario
    Given que ingreso a la web de Novus Technology
    When Ingreso el campo nombre "Juan Jose"
    And Ingreso el campo apellido "Lopez Aliaga"
    And ingreso el pasatiempo "deporte"
    #And ingreso el pasatiempo "deporte", "musica"
    And selecciono el genero "masculino"
    And ingreso el telefono "9865321452"
    And ingreso el correo "booking@test.com"
    And selecciono el departamento "ICA"
    And selecciono la ciudad "ICA"
    And selecciono el tipo de comando "WebElement Commands"
    And ingreso un documento desde la ruta "C:\Users\FERNANDO\Downloads\prueba.pdf"
    And doy click en enviar
    Then se muestra un pop up con el mensaje
    And valido que el nombre sea el mismo que se ingreso al inicio
    And valido que el apellido sea el mismo que se ingreso al inicio
    And doy click en close

  @Prueba4
  Scenario: 004 - Un alumno ingresa los datos al formulario
    Given que ingreso a la web de Novus Technology
    When Ingreso datos del usuario
      | nombre | apellido | pasatiempo | genero    | telefono | correo        | departamento | ciudad | ruta                                   | comando         |
      | Jorge  | Paz      | deporte    | Masculino | 55555555 | jpaz@test.com | LIMA         | LIMA   | C:\Users\FERNANDO\Downloads\prueba.pdf | Switch Commands |
    And doy click en enviar
    Then se muestra un pop up con el mensaje
    And valido los datos
      | nombre | apellido |
      | Jorge  | Paz      |
    And doy click en close


  @Prueba5
  Scenario Outline: Validar datos desde el escenario en el formulario
    Given que ingreso a la web de Novus Technology
    When Ingreso el campo nombre "<nombre>"
    And Ingreso el campo apellido "<apellido>"
    And ingreso el pasatiempo "<pasatiempo>"
    #And ingreso el pasatiempo "deporte", "musica"
    And selecciono el genero "<genero>"
    And ingreso el telefono "<telefono>"
    And ingreso el correo "<correo>"
    And selecciono el departamento "<departamento>"
    And selecciono la ciudad "<ciudad>"
    And selecciono el tipo de comando "<comando>"
    And ingreso un documento desde la ruta "<ruta>"
    And doy click en enviar
    Then se muestra un pop up con el mensaje
    And valido que el nombre sea el mismo que se ingreso al inicio
    And valido que el apellido sea el mismo que se ingreso al inicio
    And doy click en close

    Examples:
      | nombre    | apellido     | pasatiempo | genero    | telefono   | correo           | departamento | ciudad | comando             | ruta                                   |
      | Juan Jose | Lopez Aliaga | deporte    | masculino | 9876789657 | booking@test.com | ICA          | ICA    | WebElement Commands | C:\Users\FERNANDO\Downloads\prueba.pdf |
      | Fernando  | Cuadros      | musica     | masculino | 9876789657 | booking@test.com | ICA          | ICA    | WebElement Commands | C:\Users\FERNANDO\Downloads\prueba.pdf |
      | Bryan     | Villegas     | lectura    | masculino | 9876789657 | booking@test.com | ICA          | ICA    | WebElement Commands | C:\Users\FERNANDO\Downloads\prueba.pdf |
      | Evelyn    | Francisco    | deporte    | masculino | 9876789657 | booking@test.com | ICA          | ICA    | WebElement Commands | C:\Users\FERNANDO\Downloads\prueba.pdf |
      | Pedro     | Bazan        | musica     | masculino | 9876789657 | booking@test.com | ICA          | ICA    | WebElement Commands | C:\Users\FERNANDO\Downloads\prueba.pdf |
