Feature: Estimar tiempo de tarea {US-8}
  Scenario: Asignar a una tarea una hora estimada
    Given que soy un empleado
    When elija una tarea sin estimacion de horas
    And ingrese <18>
    Then la tarea pasara a tener <18> horas estimadas

  Scenario: Modificar la hora estimada de una tarea
    Given que soy un empleado
    When elijo una tarea con 15 horas estimadas
    And modifiquo las horas a <17>
    Then la tarea pasara a tener <17> horas estimadas

  Scenario: se intenta modificar las horas estimadas dentro de un proyecto interrumpido
    Given un proyecto con estado "Interrumpido" de nombre "proyecto interrumpido"
    When elija una tarea con cualquier estado
    Then el sistema no permitirá modificaciones en la tarea

  Scenario: se intenta modificar las horas estimadas dentro de una tarea eliminada
    Given un proyecto con estado “En curso”
    When elija una tarea con estado “Finalizada” o “Eliminada”
    Then el sistema no permitirá modificaciones en la tarea dada
