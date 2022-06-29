Feature: Listar tareas {US-10}

  Scenario: Existe una lista de tareas asignadas a un proyecto
    Given que hay proyecto con las tareas: "Tarea-01", "Tarea-02", "Tarea-03"
    When solicito las tareas de un proyecto determinado
    Then se mostrarán las tareas: "Tarea-01", "Tarea-02", "Tarea-03"

  Scenario: Existe una lista de tareas asignadas a un proyecto
    Given que hay un proyecto con las "Tarea-01" con el estado "En curso" y "Tarea-02" con estado "Finalizada"
    When solicito buscar una tarea "En curso"
    Then se mostrarán la tarea "Tarea-01"

  Scenario: Existe una lista de tareas asignadas a un proyecto
    Given que hay un proyecto "PSA-01" con las tareas "Tarea-01" con prioridad "alta" y otra "Tarea-02" con prioridad "baja"
    When solicito buscar las tareas con prioridad: "alta"
    Then se mostrarán las tarea "Tarea-01"