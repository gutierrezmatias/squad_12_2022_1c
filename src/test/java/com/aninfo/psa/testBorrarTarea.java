package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.listas.ListaDeProyectos;
import com.aninfo.psa.modelo.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class testBorrarTarea {
	
	@Autowired
    ProyectoService proyectoService;
	
	@Autowired
	TareaService tareaService;
	
	   private Proyecto proyecto;

	private Tarea unaTarea;

	@Given("hay un proyecto {string} con una tarea llamada {string} con estado {string}")
	    public void  proyectoBorrarTarea(String unNombreProyecto,String unNombreDeTarea, String unEstado) {
	    	proyecto = proyectoService.crearProyecto(new Proyecto(unNombreProyecto));
	    	unaTarea = tareaService.crear_tarea(new Tarea(unNombreDeTarea, "tarea de prueba", "Eliminarla", "alta"));
	    	proyectoService.asignar_tarea(proyecto, unaTarea.getId());
	    }
	    
	    @Given("hay un proyecto finalizado {string} con una tarea llamada {string} con estado {string}")
	    public void  proyectoFinalizadoBorrarTarea(String unNombreProyecto, String unNombreDeTarea, String unEstado) {
	    	proyecto = proyectoService.crearProyecto(new Proyecto(unNombreProyecto));
	    	unaTarea = tareaService.crear_tarea(new Tarea(unNombreDeTarea, "tarea de prueba", "Eliminarla", "alta"));
	    	proyectoService.asignar_tarea(proyecto, unaTarea.getId());
	    	proyectoService.finalizar(proyecto.getid());
	    }
	    
	    @When("solicito borrar la tarea {string}")
	    public void BorrarUnaTarea(String unNombre) {
	    	proyectoService.eliminarTarea(proyecto.getid(),unaTarea.getId());
	    }
	    
	    @Then("la tarea {string} tiene el estado {string}")
	    public void SeEliminoUnaTarea(String unNombre, String unEstado) {
	    	assertEquals(tareaService.obtener_tarea(unaTarea.getId()).get().getEstado(), unEstado);
	    }	    
	    
	}