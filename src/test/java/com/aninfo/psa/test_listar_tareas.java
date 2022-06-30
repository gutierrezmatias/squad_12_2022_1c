package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
public class test_listar_tareas {

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    TareaService tareaService;

    List<Tarea> lista_tareas;

    @Transactional
    @Given("que hay proyecto con las tareas: {string}, {string}, {string}")
    public void queHayProyectoConLasTareas(String arg0, String arg1, String arg2) {
        proyectoService.crearProyecto(new Proyecto());

        tareaService.crear_tarea(new Tarea(arg0));
        tareaService.crear_tarea(new Tarea(arg1));
        tareaService.crear_tarea(new Tarea(arg2));

        proyectoService.asignar_tarea(proyectoService.buscarPorID(1L).get(),1L);
        proyectoService.asignar_tarea(proyectoService.buscarPorID(1L).get(),2L);
        proyectoService.asignar_tarea(proyectoService.buscarPorID(1L).get(),3L);
    }

    @Transactional
    @When("solicito las tareas de un proyecto determinado")
    public void solicitoLasTareasDeUnProyectoDeterminado() {

    }


    @Transactional
    @Then("se mostrarán las tareas: {string}, {string}, {string}")
    public void seMostraránLasTareas(String arg0, String arg1, String arg2) {
        lista_tareas = proyectoService.get_tareas(1L);
        assertFalse(lista_tareas.stream().filter(tarea -> tarea.getNombre().equals(arg0)).findFirst().isEmpty());
        assertFalse(lista_tareas.stream().filter(tarea -> tarea.getNombre().equals(arg1)).findFirst().isEmpty());
        assertFalse(lista_tareas.stream().filter(tarea -> tarea.getNombre().equals(arg2)).findFirst().isEmpty());
    }

    @Transactional
    @Given("que hay un proyecto con las {string} con el estado {string} y {string} con estado {string}")
    public void queHayUnProyectoConLasConElEstadoYConEstado(String arg0, String arg1, String arg2, String arg3) {
        proyectoService.crearProyecto(new Proyecto());

        tareaService.crear_tarea(new Tarea(arg0));
        tareaService.obtener_tarea(1L).get().setEstado(arg1);

        tareaService.crear_tarea(new Tarea(arg2));
        tareaService.obtener_tarea(2L).get().setEstado(arg3);

        proyectoService.asignar_tarea(proyectoService.buscarPorID(1L).get(),1L);
        proyectoService.asignar_tarea(proyectoService.buscarPorID(1L).get(),2L);
    }

    @Transactional
    @When("solicito buscar una tarea {string}")
    public void solicitoBuscarUnaTarea(String arg0) {
        lista_tareas = proyectoService.buscarPorID(1L).get().buscar_tarea_por_estado(arg0);
    }

    @Transactional
    @Then("se mostrarán la tarea {string}")
    public void seMostraránLaTarea(String arg0) {
        assertEquals(arg0,lista_tareas.get(0).getNombre());
        assertTrue(lista_tareas.size() == 1);

    }

    @Transactional
    @Given("que hay un proyecto {string} con las tareas {string} con prioridad {string} y otra {string} con prioridad {string}")
    public void queHayUnProyectoConLasTareasConPrioridadYOtraConPrioridad(String arg0, String arg1, String arg2, String arg3, String arg4) {
        proyectoService.crearProyecto(new Proyecto());

        tareaService.crear_tarea(new Tarea(arg1));
        tareaService.obtener_tarea(1L).get().cambiar_prioridad(arg2);

        tareaService.crear_tarea(new Tarea(arg3));
        tareaService.obtener_tarea(2L).get().cambiar_prioridad(arg4);

        proyectoService.asignar_tarea(proyectoService.buscarPorID(1L).get(),1L);
        proyectoService.asignar_tarea(proyectoService.buscarPorID(1L).get(),2L);


    }

    @Transactional
    @When("solicito buscar las tareas con prioridad: {string}")
    public void solicitoBuscarLasTareasConPrioridad(String arg0) {
        lista_tareas = proyectoService.buscarPorID(1L).get().buscar_tarea_por_prioridad(arg0);
    }

    @Transactional
    @Then("se mostrarán las tarea {string}")
    public void seMostraránLasTarea(String arg0) {
        assertEquals(arg0,lista_tareas.get(0).getNombre());
        assertTrue(lista_tareas.size() == 1);
    }
}
