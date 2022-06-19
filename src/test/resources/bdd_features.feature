Feature: Se puede crear proyectos {US-5}

  Scenario: Crear un proyecto
    Given que soy un empleado
    When solicite crear un proyecto ingresare: "PSA-123","Desarrollo","Google","Alcance","14.3","Un sistema para contar visitas"
    Then el sistema creara un proyecto con :"PSA-123","Desarrollo","Google","Alcance","14.3","Un sistema para contar visitas" sin empleados, tiempo estimado, ni tareas


#Feature: Dar de baja un proyecto {US-6}
#
#  Scenario: Existe un proyecto creado
#    Given que soy un empleado
#    When solicito dar de baja un proyecto
#    Then el sistema dará de baja ese proyecto
#    And su estado cambiará a <"Interrumpido">
#
#Feature: Asignar un lider de proyecto {US-7}
#
#  Scenario: Existe un proyecto sin lider asignado
#    Given que soy un empleado
#    And elijo un proyecto
#    When solicito a recursos humanos los recursos disponibles
#    Then el sistema permitira la designación de un empleado como lider de proyecto
#
#  Scenario: Existe un proyecto con lider asignado
#    Given que soy un empleado
#    And elijo un proyecto
#    When solicito a recursos humanos los recursos disponibles
#    Then el sistema permitira la modificacion del lider del proyecto
#
#Feature: Estimar tiempo de tarea {US-8}
#  Scenario: Asignar a una tarea una hora estimada
#    Given que soy un empleado
#    When elija una tarea sin estimacion de horas
#    And ingrese <18>
#    Then la tarea pasará a tener <18> horas estimadas
#
#  Scenario: Modificar la hora estimada de una tarea
#    Given que soy un empleado
#    When elijo una tarea con 15 horas estimadas
#    And modifiquo las horas a <17>
#    Then la tarea pasará a tener <17> horas estimadas
#
#Feature: Listar proyectos {US-9}
#
#  Scenario: Existe una lista de proyectos
#    Given que soy un empleado
#    And  una lista de proyectos creados: "PSA-001", "PSA-002", "PSA-003"
#    When solicito los proyectos creados
#    Then se mostrarán los proyectos: "PSA-001", "PSA-002", "PSA-003"
#    And primero apareceran <"PSA-003"> y <"PSA-002">
#
#
#Feature: Listar tareas {US-10}
#
#  Scenario: Existe una lista de tareas asignadas a un proyecto
#    Given que hay proyecto con las tareas: "Tarea-01", "Tarea-02", "Tarea-03"
#    When solicito las tareas de un proyecto determinado
#    Then se mostrarán las tareas: "Tarea-01", "Tarea-02", "Tarea-03"
#
#  Scenario: Existe una lista de tareas asignadas a un proyecto
#    Given que hay un proyecto con las "Tarea-01" con el estado "en curso" y "Tarea-02" con estado "Finalizada"
#    When solicito buscar una tarea "en curso"
#    Then se mostrarán la tarea "Tarea-01"
#
#  Scenario: Existe una lista de tareas asignadas a un proyecto
#    Given que hay un proyecto "PSA-01" con las tareas "Tarea-01" con prioridad "alta" y otra "Tarea-02" con prioridad "baja"
#    When solicito buscar las tareas con prioridad: "alta"
#    Then se mostrarán las tarea "Tarea-01"
#
#Feature: Asignar empleados a una tarea {US-11}
#
#  Scenario: Existe un proyecto con tareas asignadas
#    Given que hay una tarea"Tarea-01"asignada a un proyecto "PSA-01"
#    And recursos humanos me dice que hay un empleadode legajo "71264" disponible
#    When asigno el empleado "71264" a la tarea
#    Then el empleado "71264" queda asignado a la tarea "Tarea-01"
#
#
#Feature: Asignar tarea a un proyecto
#  Scenario: Se le asigna una tarea a un proyecto
#    Given que hay un proyecto "PSA-01"
#    When creo una tarea llamada "Tarea-01" para el proyecto
#    Then la tarea "Tarea-01" quedará asociada al proyecto "PSA-01"