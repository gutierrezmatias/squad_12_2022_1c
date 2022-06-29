package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.modelo.Proyecto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

public class dar_de_baja_un_proyecto {
    @Autowired ProyectoService proyectoService;

    @Transactional
    @When("solicito dar de baja un proyecto")
    public void solicitoDarDeBajaUnProyecto() {
        proyectoService.crearProyecto(new Proyecto());
        proyectoService.buscarPorID(1L).get().dar_baja();
    }

    @Then("el sistema dará de baja ese proyecto")
    public void elSistemaDaráDeBajaEseProyecto() {
    }

    @And("su estado cambiará a <{string}>")
    public void suEstadoCambiaráA(String arg0) {
        assertEquals(arg0,proyectoService.buscarPorID(1L).get().getEstado());
    }
}
