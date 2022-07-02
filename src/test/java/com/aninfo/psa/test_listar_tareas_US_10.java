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
public class test_listar_tareas_US_10 {

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    TareaService tareaService;

    List<Tarea> lista_tareas;

    private Tarea tarea1;
    private Tarea tarea2;
    private Tarea tarea3;
    private Proyecto proyecto;

    @Transactional
    @Given("que hay proyecto con las tareas: {string}, {string}, {string}")
    public void queHayProyectoConLasTareas(String arg0, String arg1, String arg2) {
        proyecto = new Proyecto();
        proyectoService.crearProyecto(proyecto);

        tarea1 = new Tarea(arg0);
        tarea2 = new Tarea(arg1);
        tarea3 = new Tarea(arg2);

        tareaService.crear_tarea(tarea1);
        tareaService.crear_tarea(tarea2);
        tareaService.crear_tarea(tarea3);

        proyectoService.asignar_tarea(proyectoService.buscarPorID(proyecto.getid()).get(),tarea1.getId());
        proyectoService.asignar_tarea(proyectoService.buscarPorID(proyecto.getid()).get(),tarea2.getId());
        proyectoService.asignar_tarea(proyectoService.buscarPorID(proyecto.getid()).get(),tarea3.getId());

        assertEquals(proyectoService.buscarPorID(proyecto.getid()).get().getTarea(tarea1.getNombre()).getNombre(), tarea1.getNombre());
        assertEquals(proyectoService.buscarPorID(proyecto.getid()).get().getTarea(tarea2.getNombre()).getNombre(), tarea2.getNombre());
        assertEquals(proyectoService.buscarPorID(proyecto.getid()).get().getTarea(tarea3.getNombre()).getNombre(), tarea3.getNombre());
    }

    @Transactional
    @When("solicito las tareas de un proyecto determinado")
    public void solicitoLasTareasDeUnProyectoDeterminado() {

    }


    @Transactional
    @Then("se mostrarán las tareas: {string}, {string}, {string}")
    public void seMostraránLasTareas(String arg0, String arg1, String arg2) {
        lista_tareas = proyectoService.get_tareas(proyecto.getid());

        assertFalse(lista_tareas.stream().noneMatch(tarea -> tarea.getNombre().equals(arg0)));
        assertFalse(lista_tareas.stream().noneMatch(tarea -> tarea.getNombre().equals(arg1)));
        assertFalse(lista_tareas.stream().noneMatch(tarea -> tarea.getNombre().equals(arg2)));
    }

    //escenario 2 -----------------
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
