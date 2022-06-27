Feature: Creacion de un proyecto

  Scenario: creacion de un proyecto exitosamente
    Given que soy un empleado
    When ingreso "Proyecto-01" de "Implementacion" para el cliente "Nombre Cliente" con el alcance "un Alcance" para la version "1.0" cuya descripcion es "Proyecto de prueba"
    Then el proyecto es creado con el nombre "Proyecto-01" de "Implementacion" para el cliente "Nombre Cliente" con el alcance "un Alcance" para la version "1.0" cuya descripcion es "Proyecto de prueba" y su estado es "Pendiente"
