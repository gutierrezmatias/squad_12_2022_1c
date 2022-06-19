Feature: Asignar empleados a una tarea {US-11}

  Scenario: Existe un proyecto con tareas asignadas
    Given que hay una tarea "Tarea-01" asignada a un proyecto "PSA-01"
    And recursos humanos me dice que hay un empleadode legajo "71264" disponible
    When asigno el empleado "71264" a la tarea
    Then el empleado "71264" queda asignado a la tarea "Tarea-01"