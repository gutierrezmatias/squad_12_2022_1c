package com.aninfo.psa;

import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Tarea;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

public class test_estimar_tiempo_tarea {
    @Autowired
    TareaService tareaService;
    @When("elija una tarea sin estimacion de horas")
    public void elijaUnaTareaSinEstimacionDeHoras() {
        tareaService.crear_tarea(new Tarea());

    }

    @Transactional
    @And("ingrese <{int}>")
    public void ingrese(int arg0) {
        tareaService.obtener_tarea(1L).get().ingresar_estimado(arg0);
    }

    @Then("la tarea pasará a tener <{int}> horas estimadas")
    public void laTareaPasaráATenerHorasEstimadas(int arg0) {
        assertEquals(arg0,tareaService.obtener_tarea(1L).get().gethorasEstimadas());
    }

    @When("elijo una tarea con {int} horas estimadas")
    public void elijoUnaTareaConHorasEstimadas(int arg0) {
        tareaService.crear_tarea(new Tarea(arg0));
    }

    @Transactional
    @And("modifiquo las horas a <{int}>")
    public void modifiquoLasHorasA(int arg0) {
        tareaService.obtener_tarea(1L).get().ingresar_estimado(arg0);
    }
}
