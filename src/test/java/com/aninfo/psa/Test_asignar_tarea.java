package com.aninfo.psa;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_asignar_tarea {
    @Given("que hay un proyecto {string}")
    public void queHayUnProyecto(String arg0) {
    }

    @When("creo una tarea llamada {string} para el proyecto")
    public void creoUnaTareaLlamadaParaElProyecto(String arg0) {
    }

    @Then("la tarea {string} quedara asociada al proyecto {string}")
    public void laTareaQuedaraAsociadaAlProyecto(String arg0, String arg1) {
    }
}
