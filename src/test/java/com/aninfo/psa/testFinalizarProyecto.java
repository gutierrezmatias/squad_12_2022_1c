package com.aninfo.psa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.transaction.Transactional;

public class testFinalizarProyecto {

	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private TareaService tareaService;
	
	private Proyecto unProyecto;

	private Tarea unaTarea;
	
	@Given("hay un proyecto llamado {string} en curso")
	public void hay_un_proyecto_llamado_en_curso(String unNombreProyecto) {
		unProyecto = proyectoService.crearProyecto(new Proyecto(unNombreProyecto, "Desarrollo", "unCliente", "Algun alcance", "alguna version", "unaDescripcion"));
	    proyectoService.asignar_estado(unProyecto.getid(), "En curso");
	}

	@Transactional
	@Given("hay un proyecto llamado {string} en pendiente")
	public void hay_un_proyecto_llamado_en_pendiente(String unNombreProyecto) {
		unProyecto = proyectoService.crearProyecto(new Proyecto(unNombreProyecto, "Desarrollo", "unCliente", "Algun alcance", "alguna version", "unaDescripcion"));
		unProyecto.setEstado("Pendiente");
	}
	
	@When("finalizo el proyecto {string}")
	public void finalizo_el_proyecto(String string) {
	    proyectoService.finalizar(unProyecto.getid());
	}
	
	@Then("el estado del proyecto {string} es {string}")
	public void el_estado_del_proyecto_es(String unNombreProyeco, String unEstado) {
		 Optional<Proyecto> proyectoPrueba = proyectoService.buscarPorID(unProyecto.getid());

		proyectoPrueba.ifPresent(proyecto -> assertEquals(proyecto.getEstado(), unEstado));
	}

}
	