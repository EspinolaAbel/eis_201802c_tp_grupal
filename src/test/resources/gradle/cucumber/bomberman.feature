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
    And Una pared de melamina en x:"0" y:"2"
    When Bomberman pone una Bomba en la celda donde esta ubicado con "3" ticks
    Then La bomba explota despues de "3" ticks de bomberman y tiene onda expansiva con radio de 3 celdas
    Then La pared de melamina en x:"0" y:"2" desaparece por la onda expansiva

    Given Bomberman y un laberinto de "4" por "4"
    And Un controlador de ticks
    And Un enemigo en posicion x:"2" y:"0"
    When Bomberman pone una Bomba en la celda donde esta ubicado con "3" ticks
    Then La bomba explota despues de "3" ticks de bomberman y tiene onda expansiva con radio de 3 celdas
    Then El enemigo de la posicion x:"2" y:"2" muere por la onda expansiva

    Given Bomberman y un laberinto de "4" por "4"
    And Un controlador de ticks
    And Una pared de acero en x:"2" y:"3"
    When Bomberman pone una Bomba en la celda donde esta ubicado con "3" ticks
    Then La bomba explota despues de "3" ticks de bomberman y tiene onda expansiva con radio de 3 celdas
    Then La pared de acero en x:"2" y:"3" no desaparece por la onda expansiva


  Scenario: Bomberman mata a bagulaa para obtener un Power nuevo

    Given Bomberman y un laberinto de "6" por "6"
    And Un controlador de ticks
    And Un enemigo de tipo Bagulaa en la posicion x:"1" y:"0"
    When Bomberman pone una Bomba en la celda donde esta ubicado con "2" ticks
    Then La bomba explota despues de "2" ticks de bomberman y tiene onda expansiva con radio de 3 celdas
    And La bomba mata al enemigo en la posicion x:"1" y:"0"
    And El enemigo suelta un poder en la posicion x:"1" y:"0" donde muere

    Given Bomberman y un laberinto de "4" por "4"
    And Un Power de tipo Lanzar Bombas en la posicion x:"0" y:"1"
    When Bomberman se desplaza a la celda x:"0" y:"1"
    Then Bomberman tiene el Power de tipo Lanzar Bombas

    Given Bomberman y un laberinto de "4" por "4"
    And Un controlador de ticks
    And Bomberman con Power de tipo Lanzar Bombas
    When Bomberman Lanza bomba con "3" ticks y "2" celdas hacia "Norte"
    Then La bomba se mueve "2" celdas mas al "Norte" que bomberman


  Scenario: Bomberman mata a Proto Max Jr por lo que obtiene un poder que le permite saltar todo tipo de pared

    Given Bomberman y un laberinto de "6" por "6"
    And Un controlador de ticks
    And Un enemigo de tipo Proto Max Jr en la posicion x:"1" y:"0"
    When Bomberman pone una Bomba en la celda donde esta ubicado con "2" ticks
    Then La bomba explota despues de "2" ticks de bomberman y tiene onda expansiva con radio de 3 celdas
    And La bomba mata al enemigo en la posicion x:"1" y:"0"
    And El enemigo suelta el poder para saltar todo tipo de pared en la posicion x:"1" y:"0" donde muere

    Given Bomberman y un laberinto de "4" por "4"
    And Un Power de tipo JumpAnyWall en la posicion x:"0" y:"1"
    When Bomberman se desplaza a la celda x:"0" y:"1"
    Then Bomberman tiene el Power de tipo saltar cualquier pared

    Given Bomberman y un laberinto de "4" por "4"
    And Un controlador de ticks
    And Una pared de melamina en x:"1" y:"0"
    And Bomberman con Power de tipo JumpAnyWall
    When Bomberman se desplaza hacia el este
    Then Bomberman debe quedar en la celda x:"2" y:"0"

  Scenario: Bomberman mata a 'Proto-Max Units' y consigue el Power 'JumpOrMultibomb'

    Given Bomberman y un laberinto de "6" por "6"
    And Un controlador de ticks
    And Un enemigo de tipo Proto-Max Units en la posicion x:"1" y:"0"
    When Bomberman pone una Bomba en la celda donde esta ubicado con "2" ticks
    Then La bomba explota despues de "2" ticks de bomberman y tiene onda expansiva con radio de 3 celdas
    And La bomba mata al enemigo en la posicion x:"1" y:"0"
    And El enemigo suelta un poder en la posicion x:"1" y:"0" donde muere

    Given Bomberman y un laberinto de "4" por "4"
    And Un Power de tipo JumpOrMultiBomb en la posicion x:"0" y:"1"
    When Bomberman se desplaza a la celda x:"0" y:"1"
    Then Bomberman tiene el Power de tipo JumpOrMultibomb

    Given Bomberman y un laberinto de "10" por "10"
    And Un controlador de ticks
    And Bomberman con Power de tipo JumpOrMultibomb
    When Bomberman Lanza bomba con "3" ticks y "2" celdas hacia "Este"
    Then Hay una bomba "2" celdas mas al "Este" de donde esta Bomberman
    Then Hay una bomba "5" celdas mas al "Este" de donde esta Bomberman

    Given Bomberman y un laberinto de "4" por "4"
    And Un controlador de ticks
    And Una pared de melamina en x:"1" y:"0"
    And Bomberman con Power de tipo JumpOrMultibomb
    When Bomberman se desplaza hacia el este
    Then Bomberman debe quedar en la celda x:"2" y:"0"
