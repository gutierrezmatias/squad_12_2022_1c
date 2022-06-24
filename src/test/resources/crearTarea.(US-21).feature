Feature: Creacion De Tareas

  Scenario: Crear una Tarea
    Given que soy un empleado
    When solicito crear una tarea "tarea 01" , en un proyecto llamado "proyecto 01" con estado "en curso", con el objetivo "algun objetivo", prioridad "alta" y descripcion "descripción del proyecto"
    Then sera creada una tarea con el nombre "tarea 01" en el proyecto "proyecto 01", con el objetivo "algun objetivo", prioridad "alta", descripcion "descripcion del proyecto" y con la fecha de creacion de hoy