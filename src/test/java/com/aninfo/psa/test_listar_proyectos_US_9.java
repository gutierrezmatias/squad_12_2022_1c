package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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

    private Proyecto proyecto1;
    private Proyecto proyecto2;
    private Proyecto proyecto3;

    @Transactional
    @Given("una lista de proyectos creados: {string}, {string}, {string}")
    public void unaListaDeProyectosCreados(String arg0, String arg1, String arg2) {
        proyecto1 = new Proyecto(arg0);
        proyecto2 = new Proyecto(arg1);
        proyecto3 = new Proyecto(arg2);

        proyectoService.crearProyecto(proyecto1);
        proyectoService.crearProyecto(proyecto2);
        proyectoService.crearProyecto(proyecto3);

        proyectoService.buscarPorID(proyecto1.getid()).get().dar_baja();

        assertEquals( proyectoService.buscarPorID(proyecto1.getid()).get().getEstado(), "Interrumpido");
    }

    @Transactional
    @When("solicito los proyectos creados")
    public void solicitoLosProyectosCreados() {

        assertEquals(proyectoService.buscarPorID(proyecto1.getid()).get().getNombre(), proyecto1.getNombre());
        assertEquals(proyectoService.buscarPorID(proyecto2.getid()).get().getNombre(), proyecto2.getNombre());
        assertEquals(proyectoService.buscarPorID(proyecto3.getid()).get().getNombre(), proyecto3.getNombre());

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

       int i = 0;
       int indice1 = 0;
       int indice2 = 0;

       while(i < lista_proyectos.size()){
            if(lista_proyectos.get(i).getNombre().equals(arg0)){
                indice1 = i;
            } else if(lista_proyectos.get(i).getNombre().equals(arg1)){
                indice2 = i;
            }
            i++;
       }

       //el proyecto con estado interrumpido se muesta ultimo
       assert(indice1 > indice2);
    }


    //escenario 2------------------
    @Transactional
    @When("solicite buscar los proyectos con {string}")
    public void soliciteBuscarLosProyectosCon(String arg0) {
        lista_proyectos = proyectoService.buscadorPorString(arg0);
    }

    @Transactional
    @Then("se mostraran los proyectos: {string}, {string}")
    public void seMostraranLosProyectos(String arg0, String arg1) {

        assertEquals(lista_proyectos.size(), 2);

        assertEquals(lista_proyectos.get(0).getNombre(), arg0);
        assertEquals(lista_proyectos.get(1).getNombre(), arg1);
    }
}
