Feature: Bomberman Behavior

  Scenario: Bomberman se mueve de una celda a la contigua

    Given Bomberman y un laberinto de "5" por "5"

    When  intenta desplazarse a la celda contigua vacia
    Then  Bomberman se desplaza a la celda