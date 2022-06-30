Feature: Asignar tarea a un proyecto

  Scenario: Se le asigna una tarea a un proyecto
    Given que hay un proyecto "PSA-01"
    When creo una tarea llamada "Tarea-01" para el proyecto
    Then la tarea "Tarea-01" quedara asociada al proyecto "PSA-01"

  Scenario: Se le asigna una tarea a un proyecto interrumpido
    Given que hay un proyecto "PSA-01" con el estado "Interrumpido"
    When creo una tarea "Tarea-1" y la asigno al proyecto "PSA-01"
    Then la tarea no se asignara

  Scenario: Se intenta cambiar de proyecto una tarea que esta asignada a un proyecto interrumpido
    Given exista una tarea con nombre "Tarea-03" asignada a un proyecto con nombre "PSA-03" con estado "Interrumpido"
    And  un proyecto con nombre "PSA-04", con estado “En curso”
    When asigne la tarea con nombre "Tarea-03" al proyecto "PSA-04"
    Then la tarea con nombre "Tarea-03" no se asociará al nuevo proyecto


