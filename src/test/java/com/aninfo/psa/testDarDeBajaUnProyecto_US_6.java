package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Recurso;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

public class testDarDeBajaUnProyecto_US_6 {

    private Proyecto proyecto1;
    private Proyecto proyecto2;

    private Recurso lider;

    @Autowired
    ProyectoService proyectoService;

    private Proyecto proyecto3;
    private Proyecto proyecto4;

    //escenario 1------------------
    @Transactional
    @Given("un proyecto creado con estado “en curso”")
    public void un_proyecto_creado_con_estado_en_curso() {

        //unitario
        proyecto1 = new Proyecto("proyecto 1 en curso", "implementacion", "cliente", "alcance", "version", "descripcion");

        assertEquals(proyecto1.getEstado(), "Pendiente");

        lider = new Recurso("lider 1");
        proyecto1.asignar_lider(lider);

        assertEquals(proyecto1.getEstado(), "En curso");

        //integral
        proyecto3 = new Proyecto("proyecto 3 en curso", "implementacion", "cliente", "alcance", "version", "descripcion");
        proyectoService.crearProyecto(proyecto3);

        assertEquals(proyectoService.BuscarPorNombre(proyecto3.getNombre()).get(0).getEstado(), proyecto3.getEstado());

        proyectoService.BuscarPorNombre(proyecto3.getNombre()).get(0).asignar_lider(lider);

        assertEquals(proyectoService.BuscarPorNombre(proyecto3.getNombre()).get(0).getEstado(), "En curso");
    }

    @Transactional
    @When("solicite dar de baja ese proyecto")
    public void solicite_dar_de_baja_ese_proyecto() {

        //unitario
        proyecto1.dar_baja();

        //integral
        proyectoService.BuscarPorNombre(proyecto3.getNombre()).get(0).dar_baja();
    }

    @Transactional
    @Then("el sistema cambiará su estado a “interrumpido”")
    public void el_sistema_cambiará_su_estado_a_interrumpido() {

        //unitario
        assertEquals(proyecto1.getEstado(), "Interrumpido");

        //integral
        assertEquals(proyectoService.BuscarPorNombre(proyecto3.getNombre()).get(0).getEstado(), "Interrumpido");
    }

    //escenario 2------------------
    @Transactional
    @Given("un proyecto con estado “interrumpido” de nombre {string}")
    public void un_proyecto_con_estado_interrumpido_de_nombre(String string) {

        //unitario
        proyecto2 = new Proyecto("proyecto 2 interrumpido", "implementacion", "cliente", "alcance", "version", "descripcion");

        assertEquals(proyecto2.getEstado(), "Pendiente");

        proyecto2.dar_baja();

        assertEquals(proyecto2.getEstado(), "Interrumpido");

        //integral
        proyecto4 = new Proyecto("proyecto 4 interrumpido", "implementacion", "cliente", "alcance", "version", "descripcion");
        proyectoService.crearProyecto(proyecto4);

        assertEquals(proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).getEstado(), proyecto4.getEstado());

        proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).dar_baja();

        assertEquals(proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).getEstado(), "Interrumpido");
    }

    @Transactional
    @When("solicite dar de baja el proyecto")
    public void solicite_dar_de_baja_el_proyecto() {

        //unitario
        proyecto2.dar_baja();

        //integral
        proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).dar_baja();
    }

    @Transactional
    @Then("el sistema no cambiará su estado")
    public void el_sistema_no_cambiará_su_estado() {

        //unitario
        assertEquals(proyecto2.getEstado(), "Interrumpido");

        //integral
        assertEquals(proyectoService.BuscarPorNombre(proyecto4.getNombre()).get(0).getEstado(), "Interrumpido");
    }
}
