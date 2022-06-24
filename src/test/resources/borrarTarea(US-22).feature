Feature: Borrar Tareas

  Scenario: Borrar una tarea exitosamente
    Given que soy un empleado
    And hay un proyecto "proyecto 01" con una tarea llamada "Tarea 01" con estado "pendiente"
    When solicito borrar la tarea "Tarea 01"
    Then la tarea "Tarea 01" tiene el estado "eliminada"
    
    Scenario: Borrar una tarea de un proyecto finalizado
    Given que soy un empleado
    And hay un proyecto finalizado "proyecto 01" con una tarea llamada "Tarea 01" con estado "finalizada"
    When solicito borrar la tarea "Tarea 01"
    Then la tarea "Tarea 01" tiene el estado "finalizada"