Feature: Dar de baja un proyecto {US-6}

  Scenario: Existe un proyecto creado
    Given que soy un empleado
    When solicito dar de baja un proyecto
    Then el sistema dara de baja ese proyecto
    And su estado cambiara a <"Interrumpido">

