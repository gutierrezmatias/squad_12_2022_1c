package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class test_listar_proyectos_US_9 {

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    TareaService tareaService;

    List<Proyecto> lista_proyectos;

    @Transactional
    @And("una lista de proyectos creados: {string}, {string}, {string}")
    public void unaListaDeProyectosCreados(String arg0, String arg1, String arg2) {
        proyectoService.crearProyecto(new Proyecto(arg0));
        proyectoService.crearProyecto(new Proyecto(arg1));
        proyectoService.crearProyecto(new Proyecto(arg2));

        proyectoService.buscarPorID(1L).get().dar_baja();
    }

    @Transactional
    @When("solicito los proyectos creados")
    public void solicitoLosProyectosCreados() {
        lista_proyectos = proyectoService.obtenerProyectos();
    }

    @Transactional
    @Then("se mostrarán los proyectos: {string}, {string}, {string}")
    public void seMostraránLosProyectos(String arg0, String arg1, String arg2) {
        assertFalse(!lista_proyectos.stream().filter(proyecto -> proyecto.getNombre().equals(arg0)).findFirst().isPresent());
        assertFalse(!lista_proyectos.stream().filter(proyecto -> proyecto.getNombre().equals(arg1)).findFirst().isPresent());
        assertFalse(!lista_proyectos.stream().filter(proyecto -> proyecto.getNombre().equals(arg2)).findFirst().isPresent());
    }

    @Transactional
    @And("primero apareceran <{string}> y <{string}>")
    public void primeroApareceranY(String arg0, String arg1) {
        assertEquals(arg0,lista_proyectos.get(1).getNombre());
        assertEquals(arg1,lista_proyectos.get(0).getNombre());
    }

    @Transactional
    @When("solicite buscar los proyectos con {string}")
    public void soliciteBuscarLosProyectosCon(String arg0) {
        lista_proyectos = proyectoService.buscadorPorString(arg0);
    }

    @Transactional
    @Then("se mostraran los proyectos: {string}, {string}")
    public void seMostraranLosProyectos(String arg0, String arg1) {
        assertEquals(lista_proyectos.get(0).getNombre(), arg0);
        assertEquals(lista_proyectos.get(1).getNombre(), arg1);
    }
}
