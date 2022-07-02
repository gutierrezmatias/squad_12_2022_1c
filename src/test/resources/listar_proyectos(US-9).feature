Feature: Listar proyectos {US-9}

  Scenario: Existe una lista de proyectos y uno de ellos no esta activo
    Given una lista de proyectos creados: "PSA-001", "PSA-002", "PSA-003"
    When solicito los proyectos creados
    Then se mostrarán los proyectos: "PSA-001", "PSA-002", "PSA-003"
    And primero apareceran <"PSA-003"> y <"PSA-002">

  Scenario: Exista una lista de proyectos y se buscan aquellos que contengan cierto string
    Given una lista de proyectos creados: "google project", "Orgs project", "google product"
    When solicite buscar los proyectos con "proj"
    Then se mostraran los proyectos: "google project", "Orgs project"