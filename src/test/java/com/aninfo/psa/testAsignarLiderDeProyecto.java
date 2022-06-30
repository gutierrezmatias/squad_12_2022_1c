package com.aninfo.psa;

import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Recurso;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class testAsignarLiderDeProyecto {

    private Proyecto proyecto1;
    private Proyecto proyecto2;
    private Proyecto proyecto3;

    private Recurso lider;
    private Recurso otroLider;


    //escenario 1--------------------------
    @Given("un proyecto sin líder asignado")
    public void un_proyecto_sin_líder_asignado() {

        //unitario
        proyecto1 = new Proyecto("proyecto sin lider", "implementacion", "cliente", "alcance", "version", "descripcion");

        assertEquals(proyecto1.getEstado(), "Pendiente");
        assertNull(proyecto1.getLider());
    }

    @When("solicite a Recursos Humanos los recursos disponibles")
    public void solicite_a_recursos_humanos_los_recursos_disponibles() {
        //implementar con la integracion de rrhh

        lider = new Recurso("lider 1");
    }

    @Then("el sistema permitirá la designación de un empleado como líder de ese proyecto")
    public void el_sistema_permitirá_la_designación_de_un_empleado_como_líder_de_ese_proyecto() {

        //unitario
        proyecto1.asignar_lider(lider);

        assertEquals(proyecto1.getLider(), lider);
    }

    //escenario 2--------------------------

    @Given("un proyecto con líder asignado")
    public void un_proyecto_con_líder_asignado() {
        proyecto2 = new Proyecto("proyecto con lider", "implementacion", "cliente", "alcance", "version", "descripcion");

        //unitario
        proyecto2.asignar_lider(lider);

        assertEquals(proyecto2.getLider(), lider);
    }

    @When("solicite a Recursos Humanos los empleados disponibles")
    public void solicite_a_recursos_humanos_los_empleados_disponibles() {

        //implementar con la integracion de rrhh
        otroLider = new Recurso("lider 2");
    }

    @Then("el sistema permitirá la modificación del líder asignado por un recurso elegido para ese proyecto")
    public void el_sistema_permitirá_la_modificación_del_líder_asignado_por_un_recurso_elegido_para_ese_proyecto() {

        //unitario
        proyecto2.asignar_lider(otroLider);

        assertEquals(proyecto2.getLider(), otroLider);
    }



    //escenario 3--------------------------
    @Given("un proyecto con estado “interrumpido”")
    public void un_proyecto_con_estado_interrumpido() {

        //unitario
        proyecto3 = new Proyecto("proyecto interrumpido", "implementacion", "cliente", "alcance", "version", "descripcion");

        assertEquals(proyecto3.getEstado(), "Pendiente");

        proyecto3.dar_baja();

        assertEquals(proyecto3.getEstado(), "Interrumpido");
    }

    @When("solicite los recursos disponibles")
    public void solicite_los_recursos_disponibles() {

        lider = new Recurso("falso lider");
    }

    @Then("el sistema no permitirá la modificación del líder asignado en ese proyecto")
    public void el_sistema_no_permitirá_la_modificación_del_líder_asignado_en_ese_proyecto() {

        //untario
        assertNull(proyecto3.getLider());

        proyecto3.asignar_lider(lider);

        assertNull(proyecto3.getLider());

    }


}

