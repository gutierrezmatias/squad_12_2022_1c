package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.listas.ListaDeProyectos;
import com.aninfo.psa.modelo.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class testBorrarTarea {
	   	private Proyecto proyecto;
		private Proyecto proyecto2;

		@Autowired
		ProyectoService proyectoService;

		@Autowired
		TareaService tareaService;

		private Tarea unaTarea;

		@Given("hay un proyecto {string} con una tarea llamada {string} con estado {string}")
	    public void  proyectoBorrarTarea(String unNombreProyecto,String unNombre, String unEstado) {

			proyecto = new Proyecto(unNombreProyecto);
	    	proyecto.add_tarea(new Tarea (unNombre, "Descripcion", "objetivo", "Alta"));

	    	assertEquals(proyecto.getTarea(unNombre).getNombre(), unNombre);

	    	//integral
	    	proyecto = proyectoService.crearProyecto(new Proyecto(unNombreProyecto));
	    	unaTarea = tareaService.crear_tarea(new Tarea(unNombre, "tarea de prueba", "Eliminarla", "alta"));
	    	proyectoService.asignar_tarea(proyecto, unaTarea.getId());
	    }
	    
	    @When("solicito borrar la tarea {string}")
	    public void BorrarUnaTarea(String unNombre) {
			assertEquals(proyecto.getEstado(), "Pendiente");
			assertEquals(proyecto.getTarea(unNombre).getEstado(), "Pendiente");

			proyecto.borrarTarea(unNombre);
			assertEquals(proyecto.getTarea(unNombre).getEstado(), "Eliminada");

            //integral
			proyectoService.eliminarTarea(proyecto.getid(),unaTarea.getId());
	    }
	    
	    @Then("la tarea {string} tiene el estado {string}")
	    public void SeEliminoUnaTarea(String unNombre, String unEstado) {
			assertEquals(proyecto.getEstado(), "Pendiente");
	    	assertEquals(proyecto.getTarea(unNombre).getEstado(), unEstado);

	    	//integral
			assertEquals(tareaService.obtener_tarea(unaTarea.getId()).get().getEstado(), unEstado);
	    }

	@Given("hay un proyecto finalizado {string} con una tarea llamada {string} con estado {string}")
	public void  proyectoFinalizadoBorrarTarea(String unNombreProyecto, String unNombre, String unEstado) {

		//unitario
		proyecto2 = new Proyecto(unNombreProyecto);
		proyecto2.add_tarea(new Tarea(unNombre, "Descripcion", "objetivo", "Alta"));

		assertEquals(proyecto2.getTarea(unNombre).getNombre(), unNombre);

		proyecto2.finalizar();

		//chequeo que su tareas estan finalizdas:
		assertEquals(proyecto2.getEstado(), "Finalizado");
		assertEquals(proyecto2.getTarea(unNombre).getEstado(), "Finalizada");

		//integral
		proyecto = proyectoService.crearProyecto(new Proyecto(unNombreProyecto));
		unaTarea = tareaService.crear_tarea(new Tarea(unNombre, "tarea de prueba", "Eliminarla", "alta"));
		proyectoService.asignar_tarea(proyecto, unaTarea.getId());
		proyectoService.finalizar(proyecto.getid());
	}

	@When("solicito borrar la tarea con nombre {string}")
	public void BorrarOtraTarea(String unNombre) {
		assertEquals(proyecto2.getEstado(), "Finalizado");
		assertEquals(proyecto2.getTarea(unNombre).getEstado(), "Finalizada");

		proyecto2.borrarTarea(unNombre);
		assertEquals(proyecto2.getTarea(unNombre).getEstado(), "Finalizada");
	}


	@Then("la tarea {string} queda con el estado {string}")
	public void SeEliminoOtraTarea(String unNombre, String unEstado) {
		assertEquals(proyecto2.getEstado(), "Finalizado");
		assertEquals(proyecto2.getTarea(unNombre).getEstado(), unEstado);

	}

}