Feature: Asignar un lider de proyecto {US-7}

  Scenario: se asigna un lider
    Given un proyecto sin líder asignado
    When solicite a Recursos Humanos los recursos disponibles
    Then el sistema permitirá la designación de un empleado como líder de ese proyecto

  Scenario: se cambia el lider de proyecto
    Given un proyecto con líder asignado
    When solicite a Recursos Humanos los empleados disponibles
    Then el sistema permitirá la modificación del líder asignado por un recurso elegido para ese proyecto

  Scenario: se intenta cambiar el lider de un proyecto interrumpido
    Given un proyecto con estado “interrumpido”
    When solicite los recursos disponibles
    Then el sistema no permitirá la modificación del líder asignado en ese proyecto
