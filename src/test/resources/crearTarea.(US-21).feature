Feature: Creacion De Tareas

  Scenario: Crear una Tarea
    Given que soy un empleado
    When solicito crear una tarea, en un proyecto llamado "proyecto 01", objetivo "algun objetivo", prioridad "alta" y descripcion "descripción del proyecto"
    Then sera creado un proyecto con el nombre "proyecto 01", el objetivo "algun objetivo", prioridad "alta", descripcion "descripcion del proyecto" y con la fecha de creacion de hoy