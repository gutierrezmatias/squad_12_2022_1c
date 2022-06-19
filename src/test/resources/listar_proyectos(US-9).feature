Feature: Listar proyectos {US-9}

  Scenario: Existe una lista de proyectos y uno de ellos no esta activo
    Given que soy un empleado
    And  una lista de proyectos creados: "PSA-001", "PSA-002", "PSA-003"
    When solicito los proyectos creados
    Then se mostrarán los proyectos: "PSA-001", "PSA-002", "PSA-003"
    And primero apareceran <"PSA-003"> y <"PSA-002">