Feature: Creacion De Tareas {US-21}

  Scenario: Crear una Tarea
    Given que soy un empleado
    And hay un proyecto llamado "Proyecto-01" con estado "en curso"
    When solicito crear una tarea "tarea 01" en el proyecto "Proyecto-01", con el objetivo "algun objetivo", prioridad "alta" y descripcion "descripcion del proyecto"
    Then sera creada una tarea con el nombre "tarea 01" en el proyecto "Proyecto-01", con el objetivo "algun objetivo", prioridad "alta", descripcion "descripcion del proyecto" y con la fecha de creacion de hoy