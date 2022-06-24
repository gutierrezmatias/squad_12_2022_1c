package com.aninfo.psa;

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

import static org.junit.Assert.*;

public class testBorrarTarea {
	   private Proyecto proyecto;

	@Given("hay un proyecto {string} con una tarea llamada {string} con estado {string}")
	    public void  proyectoBorrarTarea(String unNombreProyecto,String unNombre, String unEstado) {
	    	proyecto = new Proyecto(unNombreProyecto);
	    	proyecto.add_tarea(new Tarea (unNombre, "Descripcion", "objetivo", "Alta"));
	    }
	    
	    @Given("hay un proyecto finalizado {string} con una tarea llamada {string} con estado {string}")
	    public void  proyectoFinalizadoBorrarTarea(String unNombreProyecto, String unNombre, String unEstado) {
	    	proyecto = new Proyecto(unNombreProyecto);
	    	proyecto.add_tarea(new Tarea (unNombre, "Descripcion", "objetivo", "Alta"));
	    	proyecto.finalizar();
	    }
	    
	    @When("solicito borrar la tarea {string}")
	    public void BorrarUnaTarea(String unNombre) {
	    	proyecto.borrarTarea(unNombre);
	    }
	    
	    @Then("la tarea {string} tiene el estado {string}")
	    public void SeEliminoUnaTarea(String unNombre, String unEstado) {
	    	assertEquals(proyecto.getTarea(unNombre).getEstado(), unEstado);
	    }	    
	    
	}