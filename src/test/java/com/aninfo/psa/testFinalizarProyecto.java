package com.aninfo.psa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.aninfo.psa.modelo.Recurso;
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
	private Proyecto proyecto1;
	private Proyecto proyecto2;

	private Tarea unaTarea;

	@Transactional
	@Given("hay un proyecto llamado {string} en curso")
	public void hay_un_proyecto_llamado_en_curso(String unNombreProyecto) {
		proyecto1 = new Proyecto(unNombreProyecto, "Desarrollo", "unCliente", "Algun alcance", "alguna version", "unaDescripcion");
		unProyecto = proyectoService.crearProyecto(proyecto1);

		Recurso lider = new Recurso("lider");
		proyectoService.buscarPorID(proyecto1.getid()).get().asignar_lider(lider);

	    proyectoService.asignar_estado(unProyecto.getid(), "En curso");

	    assertEquals(unProyecto.getEstado(), "En curso");
	    assertEquals(proyectoService.buscarPorID(proyecto1.getid()).get().getEstado(), "En curso");
	}

	@Transactional
	@Given("hay un proyecto llamado {string} en pendiente")
	public void hay_un_proyecto_llamado_en_pendiente(String unNombreProyecto) {
		proyecto2 = new Proyecto(unNombreProyecto, "Desarrollo", "unCliente", "Algun alcance", "alguna version", "unaDescripcion");
		unProyecto = proyectoService.crearProyecto(proyecto2);
		unProyecto.setEstado("Pendiente");

		assertEquals(unProyecto.getEstado(), "Pendiente");
	}

	@Transactional
	@When("finalizo el proyecto {string}")
	public void finalizo_el_proyecto(String string) {
	    proyectoService.finalizar(unProyecto.getid());
	}

	@Transactional
	@Then("el estado del proyecto {string} es {string}")
	public void el_estado_del_proyecto_es(String unNombreProyeco, String unEstado) {
		 Optional<Proyecto> proyectoPrueba = proyectoService.buscarPorID(unProyecto.getid());

		proyectoPrueba.ifPresent(proyecto -> assertEquals(proyecto.getEstado(), unEstado));
	}

}
	