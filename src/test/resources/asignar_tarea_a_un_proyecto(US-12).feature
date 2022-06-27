Feature: Asignar tarea a un proyecto

  Scenario: Se le asigna una tarea a un proyecto
    Given que hay un proyecto "PSA-01"
    When creo una tarea llamada "Tarea-01" para el proyecto
    Then la tarea "Tarea-01" quedara asociada al proyecto "PSA-01"

  Scenario: Se le asigna una tarea a un proyecto interrumpido
    Given que hay un proyecto "PSA-01" con el estado "Interrumpido"
    When creo una tarea "Tarea-1" y la asigno al proyecto "PSA-01"
    Then la tarea no se asignara

  Scenario: A una tarea se le asigna otro proyecto en curso
    Given que hay una tarea asignada a un proyecto con un estado distinto de "Pendiente"
    When asigne la tarea a un proyecto con estado "En curso"
    Then la tarea no se asociara al proyecto

