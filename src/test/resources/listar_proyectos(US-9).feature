Feature: Listar proyectos {US-9}

  Scenario: Existe una lista de proyectos y uno de ellos no esta activo
    Given que soy un empleado
    And  una lista de proyectos creados: "PSA-001", "PSA-002", "PSA-003"
    When solicito los proyectos creados
    Then se mostrarán los proyectos: "PSA-001", "PSA-002", "PSA-003"
    And primero apareceran <"PSA-003"> y <"PSA-002">

  Scenario: Exista una lista de proyectos y se buscan aquellos que contengan cierto string
    Given que soy un empleado
    And una lista de proyectos creados: "Un proyecto de google", "Proyecto para organizaciones", "Product para google"
    When solicite buscar los proyectos con "proy"
    Then se mostraran los proyectos: "Un proyecto de google", "Proyecto para organizaciones"