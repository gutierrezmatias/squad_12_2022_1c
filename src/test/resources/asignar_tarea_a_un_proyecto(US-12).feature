Feature: Asignar tarea a un proyecto
  Scenario: Se le asigna una tarea a un proyecto
    Given que hay un proyecto "PSA-01"
    When creo una tarea llamada "Tarea-01" para el proyecto
    Then la tarea "Tarea-01" quedará asociada al proyecto "PSA-01"