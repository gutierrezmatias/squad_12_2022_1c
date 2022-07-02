package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Recurso;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

public class testAsignarLiderDeProyecto_US_7 {

    private Proyecto proyecto1;
    private Proyecto proyecto2;
    private Proyecto proyecto3;

    private Recurso lider;
    private Recurso otroLider;

    @Autowired
    ProyectoService proyectoService;


    private Proyecto proyecto4;
    private Proyecto proyecto5;
    private Proyecto proyecto6;

    //escenario 1--------------------------
    @Transactional
    @Given("un proyecto sin líder asignado")
    public void un_proyecto_sin_líder_asignado() {

        //unitario
        proyecto1 = new Proyecto("proyecto sin lider", "implementacion", "cliente", "alcance", "version", "descripcion");

        assertEquals(proyecto1.getEstado(), "Pendiente");
        assertNull(proyecto1.getLider());


        //integral
        proyecto4 = new Proyecto("proyecto 4 sin lider ", "implementacion", "cliente", "alcance", "version", "descripcion");
        proyectoService.crearProyecto(proyecto4);

        assertEquals(proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).getNombre(), proyecto4.getNombre());
        assertEquals(proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).getEstado(), "Pendiente");
        assertNull(proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).getLider());
    }

    @Transactional
    @When("solicite a Recursos Humanos los recursos disponibles")
    public void solicite_a_recursos_humanos_los_recursos_disponibles() {
        //implementar con la integracion de rrhh

        lider = new Recurso("lider 1");
    }

    @Transactional
    @Then("el sistema permitirá la designación de un empleado como líder de ese proyecto")
    public void el_sistema_permitirá_la_designación_de_un_empleado_como_líder_de_ese_proyecto() {

        //unitario
        proyecto1.asignar_lider(lider);

        assertEquals(proyecto1.getLider(), lider);

        //integral
        assertEquals(proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).getNombre(), proyecto4.getNombre());
        assertEquals(proyectoService.buscarPorID(5L).get().getNombre(), proyecto4.getNombre());

        assertEquals(proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).getEstado(), "Pendiente");
        proyectoService.buscarPorID(5L).get().asignar_lider(lider);

        assertEquals(proyectoService.buscarPorID(5L).get().getLider(), lider);
    }

    //escenario 2--------------------------

    @Transactional
    @Given("un proyecto con líder asignado")
    public void un_proyecto_con_líder_asignado() {
        proyecto2 = new Proyecto("proyecto con lider", "implementacion", "cliente", "alcance", "version", "descripcion");

        //unitario
        proyecto2.asignar_lider(lider);

        assertEquals(proyecto2.getLider(), lider);

        //integral
        proyecto5 = new Proyecto("proyecto 5 con lider", "implementacion", "cliente", "alcance", "version", "descripcion");
        proyectoService.crearProyecto(proyecto5);

        assertEquals(proyectoService.buscarPorID(6L).get().getNombre(), proyecto5.getNombre());
        proyectoService.buscarPorID(6L).get().asignar_lider(lider);

        assertEquals(proyectoService.buscarPorID(6L).get().getLider(), lider);
    }

    @Transactional
    @When("solicite a Recursos Humanos los empleados disponibles")
    public void solicite_a_recursos_humanos_los_empleados_disponibles() {

        //implementar con la integracion de rrhh
        otroLider = new Recurso("lider 2");
    }

    @Transactional
    @Then("el sistema permitirá la modificación del líder asignado por un recurso elegido para ese proyecto")
    public void el_sistema_permitirá_la_modificación_del_líder_asignado_por_un_recurso_elegido_para_ese_proyecto() {

        //unitario
        proyecto2.asignar_lider(otroLider);

        assertEquals(proyecto2.getLider(), otroLider);

        //integral
        proyectoService.buscarPorID(6L).get().asignar_lider(otroLider);

        assertEquals(proyectoService.buscarPorID(6L).get().getLider(), otroLider);
    }



    //escenario 3--------------------------

    @Transactional
    @Given("un proyecto con estado “interrumpido”")
    public void un_proyecto_con_estado_interrumpido() {

        //unitario
        proyecto3 = new Proyecto("proyecto interrumpido", "implementacion", "cliente", "alcance", "version", "descripcion");

        assertEquals(proyecto3.getEstado(), "Pendiente");

        proyecto3.dar_baja();

        assertEquals(proyecto3.getEstado(), "Interrumpido");

        //integral
        proyecto6 = new Proyecto("proyecto 6 interrumpido", "implementacion", "cliente", "alcance", "version", "descripcion");
        proyectoService.crearProyecto(proyecto6);

        assertEquals(proyectoService.buscarPorID(7L).get().getNombre(), proyecto6.getNombre());

        assertEquals(proyectoService.buscarPorID(7L).get().getEstado(), "Pendiente");

        proyectoService.buscarPorID(7L).get().dar_baja();

        assertEquals(proyectoService.buscarPorID(7L).get().getEstado(), "Interrumpido");
    }

    @Transactional
    @When("solicite los recursos disponibles")
    public void solicite_los_recursos_disponibles() {

        lider = new Recurso("falso lider");
    }

    @Transactional
    @Then("el sistema no permitirá la modificación del líder asignado en ese proyecto")
    public void el_sistema_no_permitirá_la_modificación_del_líder_asignado_en_ese_proyecto() {

        //untario
        assertNull(proyecto3.getLider());

        proyecto3.asignar_lider(lider);

        assertNull(proyecto3.getLider());

        //integral
        proyectoService.buscarPorID(7L).get().asignar_lider(otroLider);

        assertNull(proyectoService.buscarPorID(7L).get().getLider());
    }


}

