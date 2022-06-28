package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class Test_asignar_tarea {

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    TareaService tareaService;

    @Autowired
    ProyectoService proyectoService2;

    private Proyecto proyecto;
    private Tarea tarea;

    private Proyecto proyecto2;

    @Transactional
    @Given("que hay un proyecto {string}")
    public void queHayUnProyecto(String nombreDeProyecto) {
        proyectoService.crearProyecto(new Proyecto(nombreDeProyecto));

        proyecto = new Proyecto(nombreDeProyecto);

        assertEquals(proyecto.getEstado(), "Pendiente");
    }

    @Transactional
    @When("creo una tarea llamada {string} para el proyecto")
    public void creoUnaTareaLlamadaParaElProyecto(String nombreTarea) {
        Long id = Long.valueOf(1);
        Long id_tarea = Long.valueOf(1);
        tareaService.crear_tarea(new Tarea(nombreTarea));
        proyectoService.asignar_tarea(proyectoService.buscarPorID(id).get(), id_tarea);

        assertEquals(nombreTarea, proyectoService.buscarPorID(id).get().getTareas().get(0).getNombre());

        tarea = new Tarea(nombreTarea, "Descripcion", "objetivo", "Alta");

        assertEquals(tarea.getEstado(), "Pendiente");

        proyecto.add_tarea(tarea);
    }
 
    @Transactional
    @Then("la tarea {string} quedara asociada al proyecto {string}")
    public void laTareaQuedaraAsociadaAlProyecto(String nombreTarea, String arg1) {
        Long id = Long.valueOf(1);
        Long id_tarea = Long.valueOf(1);
        assertEquals(nombreTarea, proyectoService.buscarPorID(id)
                .get()
                .getTareas()
                .stream()
                .filter(tarea -> tarea.getId() == id_tarea).findAny().get().getNombre());

        assertEquals(proyecto.getTarea(nombreTarea).getNombre(), tarea.getNombre());
    }

    //escenario 2------------------------------
    @Transactional
    @Given("que hay un proyecto {string} con el estado {string}")
    public void queHayUnProyectoConElEstado(String nombreProyecto, String estado) {
        proyectoService2.crearProyecto(new Proyecto(nombreProyecto));
        assertEquals(proyectoService2.buscarPorID(1L).get().getEstado(), "Pendiente");

        proyectoService2.asignar_estado(1L, estado);

        proyecto2 = new Proyecto(nombreProyecto);

        assertEquals(proyecto2.getEstado(), "Pendiente");
        proyecto2.setEstado(estado);
        assertEquals(proyecto2.getEstado(), estado);
    }

    @Transactional
    @When("creo una tarea {string} y la asigno al proyecto {string}")
    public void creoUnaTareaYLaAsignoAlProyecto(String nombreTarea, String nombreProyecto) {
        tareaService.crear_tarea(new Tarea(nombreTarea));
        proyectoService2.asignar_tarea(proyectoService2.buscarPorID(1L).get(), 1L);

        tarea = new Tarea(nombreTarea, "Descripcion", "objetivo", "Alta");
        assertEquals(tarea.getEstado(), "Pendiente");

        proyecto2.add_tarea(tarea);
    }

    @Transactional
    @Then("la tarea no se asignara")
    public void laTareaNoSeAsignara() {
        Proyecto proyectoInterrumpido = proyectoService2.buscarPorID(1L).get();

        assertEquals(proyectoInterrumpido.getEstado(), "Interrumpido");
        assertNull(proyectoInterrumpido.getTarea(tarea.getNombre()));

        assertNull(proyecto2.getTarea(tarea.getNombre()));
    }

    //escenario 3------------------------------
    @Given("que hay una tarea asignada a un proyecto con un estado distinto de {string}")
    public void que_hay_una_tarea_asignada_a_un_proyecto_con_un_estado_distinto_de(String arg0) {
        tareaService.crear_tarea(new Tarea());
        proyectoService.crearProyecto(new Proyecto());
        proyectoService.buscarPorID(1L).get().setEstado(arg0);
    }
    @When("asigne la tarea a un proyecto con estado {string}")
    public void asigne_la_tarea_a_un_proyecto_con_estado(String arg0) {
        proyectoService.crearProyecto(new Proyecto());
        tareaService.obtener_tarea(1L).get().setEstado(arg0);
        proyectoService.asignar_tarea(proyectoService.buscarPorID(2L).get(), 1L);

    }
    @Then("la tarea no se asociara al proyecto")
    public void la_tarea_no_se_asociara_al_proyecto() {
        assertTrue(proyectoService.buscarPorID(1L).get().getTareas().isEmpty());

    }
}
