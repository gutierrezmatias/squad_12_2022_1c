Feature: Asignar empleados a una tarea {US-11}

  Scenario: Existe un proyecto con tareas asignadas
    Given que hay una tarea "Tarea-01" asignada a un proyecto "PSA-01"
    And recursos humanos me dice que hay un empleadode legajo "71264" disponible
    When asigno el empleado "71264" a la tarea
    Then el empleado "71264" queda asignado a la tarea "Tarea-01"

  Scenario: Agrego un responsable mas a una tarea
    Given un proyecto con nombre "PSA-01" con la tarea "Tarea-01" asignada, con estado “en curso”
    When recursos humanos me diga que hay un empleado de legajo "71265" disponible
    Then podré asignar un empleado a la tarea "Tarea-01" del proyecto "PSA-01"

