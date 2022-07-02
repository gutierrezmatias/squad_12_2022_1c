package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Recurso;
import com.aninfo.psa.modelo.Tarea;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

public class test_estimar_tiempo_tarea_US_8 {

    @Autowired
    TareaService tareaService;

    @Autowired
    ProyectoService proyectoService;

    private Tarea tarea1;
    private Tarea tarea2;
    private Tarea tarea3;

    private Proyecto proyecto1;
    private Proyecto proyecto2;

    private Tarea tarea4;
    private Tarea tarea5;
    private Tarea tarea6;

    private Proyecto proyecto3;
    private Proyecto proyecto4;


    //escenario 1-------------
    @When("elija una tarea sin estimacion de horas")
    public void elijaUnaTareaSinEstimacionDeHoras() {
        tareaService.crear_tarea(new Tarea());

    }

    @Transactional
    @And("ingrese <{int}>")
    public void ingrese(int arg0) {
        tareaService.obtener_tarea(1L).get().ingresar_estimado(arg0);
    }

    @Then("la tarea pasara a tener <{int}> horas estimadas")
    public void la_tarea_pasara_a_tener_horas_estimadas(int arg0) {
        assertEquals(arg0,tareaService.obtener_tarea(1L).get().getHorasEstimadas());
    }

    //escenario 2-------------
    @When("elijo una tarea con {int} horas estimadas")
    public void elijoUnaTareaConHorasEstimadas(int arg0) {
        tareaService.crear_tarea(new Tarea(arg0));
    }

    @Transactional
    @And("modifiquo las horas a <{int}>")
    public void modifiquoLasHorasA(int arg0) {
        tareaService.obtener_tarea(1L).get().ingresar_estimado(arg0);
    }


    //escenario 3-------------
    @Transactional
    @Given("un proyecto con estado {string} de nombre {string}")
    public void un_proyecto_con_estado_de_nombre(String estadoProyecto, String nombreProyecto) {

        //unitario
        proyecto1 = new Proyecto(nombreProyecto, "implementacion", "cliente", "alcance", "version", "descripcion");
        tarea1 = new Tarea("nombre tarea", "Descripcion", "objetivo", "Alta");

        assertEquals(tarea1.getEstado(), "Pendiente");

        proyecto1.add_tarea(tarea1);

        assertEquals(proyecto1.getTarea(tarea1.getNombre()), tarea1);

        proyecto1.dar_baja();

        assertEquals(proyecto1.getEstado(), estadoProyecto);

        //integral
        proyecto3 = new Proyecto("proyecto interrumpido 3", "implementacion", "cliente", "alcance", "version", "descripcion");
        tarea4 = new Tarea("nombre tarea 4", "Descripcion", "objetivo", "Alta");

        tareaService.crear_tarea(tarea4);
        proyectoService.crearProyecto(proyecto3);

        //asigno la tarea en el proyecto
        proyectoService.BuscarPorNombre(proyecto3.getNombre()).get(0).add_tarea(tareaService.obtener_tarea(tarea4.getId()).get());

        assertEquals(proyectoService.buscarPorID(proyecto3.getid()).get().getTarea(tarea4.getNombre()), tarea4);

        proyectoService.buscarPorID(proyecto3.getid()).get().dar_baja();

        assertEquals(proyectoService.buscarPorID(proyecto3.getid()).get().getEstado(), estadoProyecto);

    }

    @Transactional
    @When("elija una tarea con cualquier estado")
    public void elija_una_tarea_con_cualquier_estado() {
        //tarea cargada en test anterior
    }

    @Transactional
    @Then("el sistema no permitirá modificaciones en la tarea")
    public void el_sistema_no_permitirá_modificaciones_en_la_tarea() {

        //unitario
        assertEquals(proyecto1.getTarea(tarea1.getNombre()).getHorasEstimadas(), 0);
        proyecto1.getTarea(tarea1.getNombre()).ingresar_estimado(15);

        assertEquals(proyecto1.getTarea(tarea1.getNombre()).getHorasEstimadas(), 0);

        //integral
        assertEquals(proyectoService.buscarPorID(proyecto3.getid()).get().getTarea(tarea4.getNombre()).getHorasEstimadas(), 0);

        proyectoService.buscarPorID(proyecto3.getid()).get().getTarea(tarea4.getNombre()).ingresar_estimado(15);
        assertEquals(proyectoService.buscarPorID(proyecto3.getid()).get().getTarea(tarea4.getNombre()).getHorasEstimadas(), 0);
    }

    //escenario 4-------------

    @Transactional
    @Given("un proyecto con estado “En curso”")
    public void un_proyecto_con_estado_en_curso() {

        //unitario
        proyecto2 = new Proyecto("proyecto 2 en curso", "implementacion", "cliente", "alcance", "version", "descripcion");
        Recurso lider = new Recurso("lider");

        proyecto2.asignar_lider(lider);

        assertEquals(proyecto2.getEstado(), "En curso");

        //integral
        proyecto4 = new Proyecto("proyecto 4 en curso", "implementacion", "cliente", "alcance", "version", "descripcion");

        proyectoService.crearProyecto(proyecto4);
        proyectoService.buscarPorID(proyecto4.getid()).get().asignar_lider(lider);

        assertEquals(proyectoService.buscarPorID(proyecto4.getid()).get().getEstado(), "En curso");
    }

    @Transactional
    @When("elija una tarea con estado “Finalizada” o “Eliminada”")
    public void elija_una_tarea_con_estado_finalizada_o_eliminada() {

        //unitario
        tarea2 = new Tarea("tarea 2 finalizada", "Descripcion", "objetivo", "Alta");
        tarea3 = new Tarea("tarea 3 eliminada", "Descripcion", "objetivo", "Alta");

        proyecto2.add_tarea(tarea2);
        proyecto2.add_tarea(tarea3);

        assertEquals(proyecto2.getTarea(tarea2.getNombre()), tarea2);
        assertEquals(proyecto2.getTarea(tarea3.getNombre()), tarea3);

        tarea2.finalizar();

        assertEquals(tarea2.getEstado(), "Finalizada");

        tarea3.eliminar();

        assertEquals(tarea3.getEstado(), "Eliminada");

        //integral
        tarea5 = new Tarea("tarea 5 finalizada", "Descripcion", "objetivo", "Alta");
        tarea6 = new Tarea("tarea 6 eliminada", "Descripcion", "objetivo", "Alta");

        tareaService.crear_tarea(tarea5);
        tareaService.crear_tarea(tarea6);

        //las agrego al proyecto
        proyectoService.buscarPorID(proyecto4.getid()).get().add_tarea(tareaService.obtener_tarea(tarea5.getId()).get());
        proyectoService.buscarPorID(proyecto4.getid()).get().add_tarea(tareaService.obtener_tarea(tarea6.getId()).get());

        tareaService.obtener_tarea(tarea5.getId()).get().finalizar();
        tareaService.obtener_tarea(tarea6.getId()).get().eliminar();

        assertEquals(tareaService.obtener_tarea(tarea5.getId()).get().getEstado(), "Finalizada");
        assertEquals(tareaService.obtener_tarea(tarea6.getId()).get().getEstado(), "Eliminada");
    }

    @Transactional
    @Then("el sistema no permitirá modificaciones en la tarea dada")
    public void el_sistema_no_permitirá_modificaciones_en_la_tarea_dada() {

        //unitario
        assertEquals(tarea2.getHorasEstimadas(), 0);

        tarea2.ingresar_estimado(15);
        assertEquals(tarea2.getHorasEstimadas(), 0);

        assertEquals(tarea3.getHorasEstimadas(), 0);

        tarea3.ingresar_estimado(15);
        assertEquals(tarea3.getHorasEstimadas(), 0);

        //integral
        assertEquals(proyectoService.buscarPorID(proyecto4.getid()).get().getTarea(tarea5.getNombre()).getHorasEstimadas(), 0);

        proyectoService.buscarPorID(proyecto4.getid()).get().getTarea(tarea5.getNombre()).ingresar_estimado(15);
        assertEquals(proyectoService.buscarPorID(proyecto4.getid()).get().getTarea(tarea5.getNombre()).getHorasEstimadas(), 0);

        assertEquals(proyectoService.buscarPorID(proyecto4.getid()).get().getTarea(tarea6.getNombre()).getHorasEstimadas(), 0);

        proyectoService.buscarPorID(proyecto4.getid()).get().getTarea(tarea6.getNombre()).ingresar_estimado(15);
        assertEquals(proyectoService.buscarPorID(proyecto4.getid()).get().getTarea(tarea6.getNombre()).getHorasEstimadas(), 0);
    }

}
