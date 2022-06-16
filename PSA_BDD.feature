Feature: Se puede crear proyectos {US-5}

  Scenario: Crear un proyecto
    Given que soy un empleado
    When solicite crear un proyecto ingresare: "PSA-123","Desarrollo","Google","Alcance","14.3","Un sistema para contar visitas"
    Then el sistema creara un proyecto con :"PSA-123","Desarrollo","Google","Alcance","14.3","Un sistema para contar visitas", "Alan,Marco"


Feature: Dar de baja un proyecto {US-6}

  Scenario: Existe un proyecto creado
    Given que soy un empleado
    When solicite dar de baja un proyecto
    Then el sistema dará de baja ese proyecto
    And su estado cambiará a <"Interrumpido">

Feature: Asignar un lider de proyecto {US-7}

  Scenario: Existe un proyecto sin lider asignado
    Given que soy un empleado
    And elijo un proyecto
    When solicite a recursos humanos los recursos disponibles
    Then el sistema permitira la designación de un empleado como lider de proyecto

  Scenario: Existe un proyecto con lider asignado
    Given que soy un empleado
    And elijo un proyecto
    When solicite a recursos humanos los recursos disponibles
    Then el sistema permitira la modificacion del lider del proyecto

Feature: Estimar tiempo de tarea {US-8}
  Scenario: Asignar a una tarea una hora estimada
    Given que soy un empleado
    When elija una tarea sin estimacion de horas
    And ingrese <18>
    Then la tarea pasará a tener <18> horas estimadas

  Scenario: Modificar la hora estimada de una tarea
    Given que soy un empleado
    When elija una tarea con una estimacion de horas
    And modifique las horas a <17>
    Then la tarea pasará a tener <17> horas estimadas

Feature: Listar proyectos {US-9}

  Scenario: Existe una lista de proyectos
    Given que soy un empleado
    Given una lista de proyectos creados: "PSA-001", "PSA-002", "PSA-003"
    When solicite los proyectos creados
    Then se mostrarán los proyectos: "PSA-001", "PSA-002", "PSA-003"
    And primero apareceran <"PSA-003"> y <"PSA-002">






