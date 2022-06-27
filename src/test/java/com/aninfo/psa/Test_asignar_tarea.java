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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test_asignar_tarea {
    @Autowired
    ProyectoService proyectoService;
    @Autowired
    TareaService tareaService;


    @Transactional
    @Given("que hay un proyecto {string}")
    public void queHayUnProyecto(String arg0) {
        proyectoService.crearProyecto(new Proyecto(arg0));

    }

    @Transactional
    @When("creo una tarea llamada {string} para el proyecto")
    public void creoUnaTareaLlamadaParaElProyecto(String arg0) {
        Long id = Long.valueOf(1);
        Long id_tarea = Long.valueOf(1);
        tareaService.crear_tarea(new Tarea(arg0));
        proyectoService.asignar_tarea(proyectoService.buscarPorID(id).get(), id_tarea);
        assertEquals(arg0, proyectoService.buscarPorID(id).get().getTareas().get(0).getNombre());

    }
 
    @Transactional
    @Then("la tarea {string} quedara asociada al proyecto {string}")
    public void laTareaQuedaraAsociadaAlProyecto(String arg0, String arg1) {
        Long id = Long.valueOf(1);
        Long id_tarea = Long.valueOf(1);
        assertEquals(arg0, proyectoService.buscarPorID(id)
                .get()
                .getTareas()
                .stream()
                .filter(tarea -> tarea.getId() == id_tarea).findAny().get().getNombre());
    }

    @Transactional
    @Given("que hay un proyecto {string} con el estado {string}")
    public void queHayUnProyectoConElEstado(String arg0, String arg1) {
        proyectoService.crearProyecto(new Proyecto(arg0));
        proyectoService.asignar_estado(Long.valueOf(1),arg1);
    }

    @Transactional
    @When("creo una tarea {string} y la asigno al proyecto {string}")
    public void creoUnaTareaYLaAsignoAlProyecto(String arg0, String arg1) {
        tareaService.crear_tarea(new Tarea(arg0));
        proyectoService.asignar_tarea(proyectoService.buscarPorID(Long.valueOf(1)).get(),Long.valueOf(1));
    }

    @Transactional
    @Then("la tarea no se asignara")
    public void laTareaNoSeAsignara() {
        assertTrue(proyectoService.buscarPorID(Long.valueOf(1)).get().getTareas().isEmpty());
    }

    @Transactional
    @Given("que hay una tarea asignada a un proyecto con un estado distinto de {string}")
    public void queHayUnaTareaAsignadaAUnProyectoConUnEstadoDistintoDe(String arg0) {
        tareaService.crear_tarea(new Tarea());
        proyectoService.crearProyecto(new Proyecto());
        proyectoService.buscarPorID(Long.valueOf(1)).get().setEstado(arg0);

    }

    @Transactional
    @When("asigne la tarea a un proyecto con estado {string}")
    public void asigneLaTareaAUnProyectoConEstado(String arg0) {
        proyectoService.crearProyecto(new Proyecto());
        tareaService.obtener_tarea(Long.valueOf(1)).get().setEstado(arg0);
        proyectoService.asignar_tarea(proyectoService.buscarPorID(Long.valueOf(2)).get(),Long.valueOf(1));
    }
    @Transactional
    @Then("la tarea no se asociara al proyecto")
    public void laTareaNoSeAsociaraAlProyecto() {
        assertTrue(proyectoService.buscarPorID(Long.valueOf(1)).get().getTareas().isEmpty());
    }
}
