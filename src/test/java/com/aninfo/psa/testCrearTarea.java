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


public class testCrearTarea {
	private Proyecto proyecto;

	@When("solicito crear una tarea {string} , en un proyecto llamado {string} con estado {string}, con el objetivo {string}, prioridad {string} y descripcion {string}")
    public void crearTarea(String unNombreDeTarea, String unNombreProyecto, String estadoDeUnProyecto, String objetivoDeUnaTarea, String prioridadDeUnaTarea, String descripcionDeUnaTarea) {
    	Tarea unaTarea = new Tarea(unNombreDeTarea, descripcionDeUnaTarea, objetivoDeUnaTarea, prioridadDeUnaTarea);
    	proyecto = new Proyecto(unNombreProyecto, "implementación", "cliente", "alcance", "version", "desicirpicon");
    	proyecto.add_tarea(unaTarea);
    	
    }
    
    @Then("sera creada una tarea con el nombre {string} en el proyecto {string}, con el objetivo {string}, prioridad {string}, descripcion {string} y con la fecha de creacion de hoy")
    public void verificarTarea(String unNombreDeTarea, String unNombreDeProyecto, String unObjetivoDeTarea, String unaPrioridadDeTarea, String unaDescripcionDeTarea) {
    	 Date fecha = new Date();
    	 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    	 String hoy = formatter.format(fecha);
    	 
    	 assertEquals(proyecto.getNombre(), unNombreDeProyecto);
    	 assertEquals(proyecto.getTarea(unNombreDeTarea).getNombre(), unNombreDeTarea);
    	 assertEquals(proyecto.getTarea(unNombreDeTarea).getobjetivo(), unObjetivoDeTarea);
    	 assertEquals(proyecto.getTarea(unNombreDeTarea).getPrioridad(), unaPrioridadDeTarea);
    	 assertEquals(proyecto.getTarea(unNombreDeTarea).getFechaCreacion(),hoy);
    }
    
}
