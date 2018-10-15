Feature: Bomberman Behavior

  Scenario: Bomberman se mueve de una celda a la contigua

    Given Bomberman y un laberinto de "2" por "2"
    When  intenta desplazarse a la celda contigua vacia
    Then  Bomberman se desplaza a la celda

    Given Bomberman y un laberinto de "2" por "2"
    When  intenta desplazarse a la celda ocupada por una pared en x:"1" y:"0"
    Then  Bomberman no se mueve de lugar

    Given Bomberman y un laberinto de "2" por "2" con un enemigo en una la celda contigua
    When  intenta desplazarse a la celda ocupada por un enemigo
    Then  Bomberman muere

  Scenario: Bomberman suelta una bomba donde el se encuentra

    Given Bomberman y un laberinto de "4" por "4"
    And Un controlador de ticks
    And Una pared de melamina en x:"2" y:"3"
    When Bomberman pone una Bomba en la celda donde esta ubicado con "3" ticks
    Then La bomba explota despues de "3" ticks de bomberman y tiene onda expansiva con radio de 3 celdas
    Then La pared de melamina en x:"2" y:"3" desaparece por la onda expansiva