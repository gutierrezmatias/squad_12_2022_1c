package com.aninfo.psa;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class testFinalizarTarea {

	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private TareaService tareaService;
	
	private Proyecto unProyecto;

	private Tarea unaTarea;
	
	
	@Given("hay un proyecto llamado {string} con el estado {string}")
	public void hay_un_proyecto_llamado_con_el_estado(String unNombreProyecto, String unEstadoDeProyecto) {
	    unProyecto = proyectoService.crearProyecto(new Proyecto(unNombreProyecto, "Desarrollo", "unCliente", "Algun alcance", "alguna version", "unaDescripcion"));
	    proyectoService.asignar_estado(unProyecto.getid(), "En curso");
	}
	
	@Given("el proyecto tiene una tarea llamada {string} con el estado {string}")
	public void el_proyecto_tiene_una_tarea_llamada_con_el_estado(String unNombreTarea, String unEstadoTarea) {
	    unaTarea = tareaService.crear_tarea(new Tarea(unNombreTarea, "Descripcion de tarea", "Objetivo de tarea", "Prioridad de tarea" ));
	    tareaService.asignar_estado(unaTarea.getId(), "En curso" );
	}
	
	@When("finalizo la tarea {string}")
	public void finalizo_la_tarea(String string) {
	    tareaService.asignar_estado(unaTarea.getId(), "Finalizada");
	}
	
	@Then("el estado de la {string} es {string}")
	public void el_estado_de_la_es(String string, String string2) {
	   Optional<Tarea> tareaPrueba = tareaService.obtener_tarea(unaTarea.getId());
	   
	   if (tareaPrueba.isPresent()) assertEquals(tareaPrueba.get().getEstado(), "Finalizada");
	}


}
