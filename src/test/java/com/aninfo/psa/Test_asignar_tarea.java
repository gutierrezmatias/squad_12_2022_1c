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

public class Test_asignar_tarea {
    @Autowired
    ProyectoService proyectoService;
    @Autowired
    TareaService tareaService;


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
}
