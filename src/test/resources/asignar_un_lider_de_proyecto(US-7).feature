Feature: Asignar un lider de proyecto {US-7}

  Scenario: Existe un proyecto sin lider asignado
    Given que soy un empleado
    And elijo un proyecto
    When solicito a recursos humanos los recursos disponibles
    Then el sistema permitira la designación de un empleado como lider de proyecto

  Scenario: Existe un proyecto con lider asignado
    Given que soy un empleado
    And elijo un proyecto
    When solicito a recursos humanos los recursos disponibles
    Then el sistema permitira la modificacion del lider del proyecto