package com.aninfo.psa;

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

public class test_estimar_tiempo_tarea {

    @Autowired
    TareaService tareaService;

    private Tarea tarea1;
    private Tarea tarea2;
    private Tarea tarea3;

    private Proyecto proyecto1;
    private Proyecto proyecto2;

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
        assertEquals(arg0,tareaService.obtener_tarea(1L).get().gethorasEstimadas());
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
        assertEquals(proyecto1.getTarea(tarea1.getNombre()).gethorasEstimadas(), 0);
        proyecto1.getTarea(tarea1.getNombre()).ingresar_estimado(15);

        assertEquals(proyecto1.getTarea(tarea1.getNombre()).gethorasEstimadas(), 0);
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
    }

    @Transactional
    @Then("el sistema no permitirá modificaciones en la tarea dada")
    public void el_sistema_no_permitirá_modificaciones_en_la_tarea_dada() {

        //unitario
        assertEquals(tarea2.gethorasEstimadas(), 0);

        tarea2.ingresar_estimado(15);
        assertEquals(tarea2.gethorasEstimadas(), 0);

        assertEquals(tarea3.gethorasEstimadas(), 0);

        tarea3.ingresar_estimado(15);
        assertEquals(tarea3.gethorasEstimadas(), 0);
    }

}
