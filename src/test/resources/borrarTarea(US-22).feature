Feature: Borrar Tareas {US-22}

  Scenario: Borrar una tarea exitosamente
    Given hay un proyecto "proyecto 01" con una tarea llamada "Tarea 01" con estado "Pendiente"
    When solicito borrar la tarea "Tarea 01"
    Then la tarea "Tarea 01" tiene el estado "Eliminada"
    
    Scenario: Borrar una tarea de un proyecto finalizado
    Given hay un proyecto finalizado "proyecto 01" con una tarea llamada "Tarea 01" con estado "Finalizada"
    When solicito borrar la tarea con nombre "Tarea 01"
    Then la tarea "Tarea 01" queda con el estado "Finalizada"