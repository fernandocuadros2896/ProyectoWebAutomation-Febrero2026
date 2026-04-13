Feature: Comprar con el carrito de compras

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

    #1. crear un scenario de forma declarativa en el archivo .feature
  @Carrito2
  Scenario: El usuario agrega productos y realiza una compra DECLARATIVA
    Given el usuario ingresa a la pagina de productos
    When el usuario obtiene los datos de la tarjeta
    And el usuario agrega un producto y da comprar
    And el usuario ingresa los datos de la tarjeta y paga
    Then el usuario visualiza que la compra ha sido exitosa y ve el numero de orden
    And da click en home

#2. crear un scenario que envie los datos de numero de compra desde el feature.
  @Carrito3
  Scenario: El usuario agrega productos y realiza una compra enviando de feature
    Given el usuario ingresa a la pagina de productos
    When el usuario obtiene el numero de tarjeta
    And el usuario obtiene el numero de CVV
    And el mes y año de vencimiento
    And el usario agrega la cantidad de producto "4"
    And da click en comprar
    And ingresa el numero de tarjeta
    And ingresa el numero de CVV
    And ingresa el mes y año de vencimiento
    And dar click en comprar
    Then visualiza un mensaje de compra exitosa
    And el numero de orden de compra
    And da click en home

#3. crear un scenario usando DataTable en el archivo .feature

  @Carrito4
  Scenario: El usuario agrega productos y realiza una compra con datable
    Given el usuario ingresa a la pagina de productos
    When el usuario obtiene el numero de tarjeta
    And el usuario obtiene el numero de CVV
    And el mes y año de vencimiento
    And el usuario agrega la cantidad de producto
      | cantidad |
      | 5        |
    And da click en comprar
    And ingresa el numero de tarjeta
    And ingresa el numero de CVV
    And ingresa el mes y año de vencimiento
    And dar click en comprar
    Then visualiza un mensaje de compra exitosa
    And el numero de orden de compra
    And da click en home

    #4. crear un scenario usando Scenario Outline con varios ejemplos en el archivo .feature

  @Carrito5
  Scenario Outline: El usuario agrega productos y realiza una compra Outline
    Given el usuario ingresa a la pagina de productos
    When el usuario obtiene el numero de tarjeta
    And el usuario obtiene el numero de CVV
    And el mes y año de vencimiento
    And el usuario agrega la cantidad de producto "<cantidad>"
    And da click en comprar
    And ingresa el numero de tarjeta
    And ingresa el numero de CVV
    And ingresa el mes y año de vencimiento
    And dar click en comprar
    Then visualiza un mensaje de compra exitosa
    And el numero de orden de compra
    And da click en home

    Examples:
      | cantidad |
      | 6        |

   #5 crear un scenario usando tags en el archivo .feature que carga informacion desde un archivo .csv

  @Carrito6
  Scenario: El usuario agrega productos y compra mediante CSV
    Given el usuario ingresa a la pagina de productos
    When el usuario obtiene los datos de la tarjeta
    And el usuario agrega la cantidad desde archivo csv
    And da click en comprar
    And el usuario ingresa los datos de la tarjeta y paga
    Then el usuario visualiza que la compra ha sido exitosa y ve el numero de orden
    And da click en home

 #6. agregar pagos en el escenario usar Assert y validaciones de los datos de tarjeta y compra en el check credit card limit
  @Carrito7
  Scenario: El usuario agrega productos y realiza una compra End 2 End
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
    And visualiza un mensaje de compra exitosa
    And el numero de orden de compra
    And da click en home
    And se da click en menu Check Credit Card Limit
    And se ingresa el numero de la tarjeta
    And dar click en enviar
    Then se muestra la pagina con los datos
    And validamos los datos de la tarjeta y el numero de orden


