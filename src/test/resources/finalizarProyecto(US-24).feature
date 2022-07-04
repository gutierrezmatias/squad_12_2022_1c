Feature: Finalizar un proyecto

  Scenario: Finalizar un proyecto en curso
    Given que soy un empleado
    And hay un proyecto llamado "Proyecto-01" en curso
    When finalizo el proyecto "Proyecto-01"
    Then el estado del proyecto "Proyecto-01" es "Finalizado"

	Scenario: Finalizar un proyecto en pendiente
    Given que soy un empleado
    And hay un proyecto llamado "Proyecto-01" en pendiente
    When finalizo el proyecto "Proyecto-01"
    Then el estado del proyecto "Proyecto-01" es "Pendiente"