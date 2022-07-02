package com.aninfo.psa;

import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Recurso;
import com.aninfo.psa.modelo.Tarea;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class testAsignarResponsablesDeTarea_US_11 {

    private Proyecto proyecto;
    private Tarea tarea;
    private Recurso empleado;

    private Recurso empleado2;

    @Given("que hay una tarea {string} asignada a un proyecto {string}")
    public void que_hay_una_tarea_asignada_a_un_proyecto(String nombreDeTarea, String nombreDeProyecto) {

        proyecto = new Proyecto(nombreDeProyecto);
        tarea = new Tarea(nombreDeTarea, "Descripcion", "objetivo", "Alta");
        proyecto.add_tarea(tarea);

        assertEquals(proyecto.getTarea(nombreDeTarea).getNombre(), nombreDeTarea);
    }

    @Given("recursos humanos me dice que hay un empleadode legajo {string} disponible")
    public void recursos_humanos_me_dice_que_hay_un_empleadode_legajo_disponible(String legajoEmpleado) {
        //se implementa cuando se integre con el modulo de rrhh

        empleado = new Recurso(legajoEmpleado);
    }

    @When("asigno el empleado {string} a la tarea")
    public void asigno_el_empleado_a_la_tarea(String nombreEmpleado) {

        Tarea tareaAsignada = proyecto.getTarea(tarea.getNombre());

        assertEquals(tareaAsignada, tarea);
        assertEquals(tarea.getEstado(), "Pendiente");

        tareaAsignada.asignarResponsable(empleado);
        assertEquals(tarea.getEstado(), "En curso");
    }

    @Then("el empleado {string} queda asignado a la tarea {string}")
    public void el_empleado_queda_asignado_a_la_tarea(String string, String string2) {

        assert(tarea.getRecursosAsignados().contains(empleado)); //mejorar cuando se integre con el modulo de rrhh
    }

    //escenario 2

    @Given("un proyecto con nombre {string} con la tarea {string} asignada, con estado “en curso”")
    public void un_proyecto_con_nombre_con_la_tarea_asignada_con_estado_en_curso(String nombreDeProyecto, String nombreDeTarea) {

        proyecto = new Proyecto(nombreDeProyecto);
        tarea = new Tarea(nombreDeTarea, "Descripcion", "objetivo", "Alta");
        proyecto.add_tarea(tarea);
        empleado = new Recurso("12345");
        tarea.asignarResponsable(empleado);

        assertEquals(proyecto.getTarea(nombreDeTarea).getNombre(), nombreDeTarea);
        Tarea tareaAsignada = proyecto.getTarea(tarea.getNombre());
        assertEquals(tareaAsignada.getEstado(), "En curso");
    }

    @When("recursos humanos me diga que hay un empleado de legajo {string} disponible")
    public void recursos_humanos_me_diga_que_hay_un_empleado_de_legajo_disponible(String legajo) {
        empleado2 = new Recurso(legajo);
    }

    @Then("podré asignar un empleado a la tarea {string} del proyecto {string}")
    public void podré_asignar_un_empleado_a_la_tarea_del_proyecto(String nombreDeTarea, String nombreDeProyecto) {

        Tarea tareaAsignada = proyecto.getTarea(tarea.getNombre());

        assert(tareaAsignada.getRecursosAsignados().contains(empleado));

        assertEquals(tareaAsignada, tarea);
        tareaAsignada.asignarResponsable(empleado2);

        assert(tareaAsignada.getRecursosAsignados().contains(empleado2));
    }


}
