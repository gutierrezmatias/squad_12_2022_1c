Feature: Borrar Tareas

  Scenario: Borrar una tarea exitosamente
    Given que soy un empleado
    And hay un proyecto "en curso" con una tarea llamada "Tarea 01" con estado "pendiente"
    When solicito borrar la tarea "Tarea 01"
    Then se eliminara la tara "Tarea 01"
    
    Scenario: Borrar una tarea exitosamente
    Given que soy un empleado
    And hay un proyecto "en curso" con una tarea llamada "Tarea 01" con estado "pendiente"
    When solicito borrar la tarea "Tarea 01"
    Then se eliminara la tara "Tarea 01"