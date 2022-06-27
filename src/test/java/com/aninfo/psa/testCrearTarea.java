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
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class testCrearTarea {
	
	@Autowired
    ProyectoService proyectoService;
	
	@Autowired
	TareaService tareaService;
	
	
	private Proyecto proyecto;

	private Tarea unaTarea;
	
	@Given("hay un proyecto llamado {string} con estado {string}")
	public void hay_un_proyecto_llamado_con_estado(String unNombreDeProyecto, String unEstadoDeProyecto) {
		proyecto = proyectoService.crearProyecto(new Proyecto(unNombreDeProyecto, "Implementacion", "ClienteProyecto", "Algún Alcace", "2.0", "Proyecto para la creacion de tareas"));
	}


	@When("solicito crear una tarea {string} en el proyecto {string}, con el objetivo {string}, prioridad {string} y descripcion {string}")
    public void crearTarea(String unNombreDeTarea, String unNombreProyecto, String objetivoDeUnaTarea, String prioridadDeUnaTarea, String descripcionDeUnaTarea) {
    	unaTarea = tareaService.crear_tarea(new Tarea(unNombreDeTarea, descripcionDeUnaTarea, objetivoDeUnaTarea, prioridadDeUnaTarea));
    	proyectoService.asignar_tarea(proyecto, unaTarea.getId());
    }
    
    @Then("sera creada una tarea con el nombre {string} en el proyecto {string}, con el objetivo {string}, prioridad {string}, descripcion {string} y con la fecha de creacion de hoy")
    public void verificarTarea(String unNombreDeTarea, String unNombreDeProyecto, String unObjetivoDeTarea, String unaPrioridadDeTarea, String unaDescripcionDeTarea) {
    	 Date fecha = new Date();
    	 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    	 String hoy = formatter.format(fecha);
    	 
    	 Optional<Tarea> tareaPruebaOP = tareaService.obtener_tarea(unaTarea.getId());
    	 
    	 if (tareaPruebaOP.isPresent()) {
    		 Tarea tareaPrueba = tareaPruebaOP.get();
    		 assertEquals(proyecto.getNombre(), unNombreDeProyecto);
        	 assertEquals(tareaPrueba.getNombre(), unNombreDeTarea);
        	 assertEquals(tareaPrueba.getObjetivo(), unObjetivoDeTarea);
        	 assertEquals(tareaPrueba.getPrioridad(), unaPrioridadDeTarea);
        	 assertEquals(tareaPrueba.getFechaCreacion(),hoy);
    	 }
    	 
    }
    
}
