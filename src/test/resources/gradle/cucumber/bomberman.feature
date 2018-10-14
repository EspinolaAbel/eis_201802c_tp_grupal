Feature: Bomberman Behavior

  Scenario: Bomberman se mueve de una celda a la contigua

    Given Bomberman y un laberinto de "2" por "2"
    When  intenta desplazarse a la celda contigua vacia
    Then  Bomberman se desplaza a la celda

    Given Bomberman y un laberinto de "2" por "2"
    When  intenta desplazarse a la celda ocupada por una pared
    Then  Bomberman no se mueve de lugar

    Given Bomberman y un laberinto de "2" por "2" con un enemigo en una la celda contigua
    When  intenta desplazarse a la celda ocupada por un enemigo
    Then  Bomberman muere