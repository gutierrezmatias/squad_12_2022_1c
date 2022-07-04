Feature: Finalizar una tarea

  Scenario: Finalizar una tarea exitosamente
    Given que soy un empleado
    And hay un proyecto llamado "Proyecto-01" con el estado "En curso"
    And el proyecto tiene una tarea llamada "Tarea-01" en curso"
    When finalizo la tarea "Tarea-01"
    Then el estado de la "Tarea-01" es "Finalizada"
    
	Scenario: Finalizar una tarea pendiente
		Given que soy un empleado
    And hay un proyecto llamado "Proyecto-01" con el estado "En curso"
    And el proyecto tiene una tarea llamada "Tarea-01" pendiente
    When finalizo la tarea "Tarea-01"
    Then el estado de la "Tarea-01" es "Pendiente"
    
  
