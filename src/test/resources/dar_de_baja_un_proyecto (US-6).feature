Feature: Dar de baja un proyecto {US-6}

  Scenario: baja de un proyecto
    Given un proyecto creado con estado “en curso”
    When solicite dar de baja ese proyecto
    Then el sistema cambiará su estado a “interrumpido”

  Scenario: se intenta dar de baj un proyecto interrumpido
    Given un proyecto con estado “interrumpido” de nombre "PSA-02"
    When solicite dar de baja el proyecto
    Then el sistema no cambiará su estado


