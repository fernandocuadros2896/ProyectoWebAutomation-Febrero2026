Feature: Comprar con el carrito de compras

  Scenario: El usuario agrega productos y realiza una compra
    Given el usuario ingresa a la pagina de productos
    When el usuario obtiene los datos de la tarjeta
    And el usuario agrega un producto y da comprar
    Then el usuario visualiza un campo par ingresar los datos de la tarjeta
    And puede ver los datos de compra y de la tarjeta

  @carrito1
  Scenario: El usuario agrega productos y realiza una compra
    Given el usuario ingresa a la pagina de productos
    When el usuario obtiene el numero de tarjeta
    And el usuario obtiene el numero de CVV
    And el mes y año de vencimiento
    And el usario agrega un producto
    And da click en comprar
    And ingresa el numero de tarjeta
    And ingresa el numero de CVV
    And ingresa el mes y año de vencimiento
    And dar click en comprar
    Then visualiza un mensaje de compra exitosa
    And el numero de orden de compra
    And da click en home

