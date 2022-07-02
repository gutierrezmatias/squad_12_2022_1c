package com.aninfo.psa;

import java.util.Optional;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.modelo.Proyecto;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(
        classes = PsaProyectosApplication.class,
        loader = SpringBootContextLoader.class)
public class testCrearProyecto_US_5 {
	Proyecto proyecto;
	
	@Autowired
    ProyectoService proyectoService;

	@Given("que soy un empleado")
	public void que_soy_un_empleado() {

	}

	@When("ingreso {string} de {string} para el cliente {string} con el alcance {string} para la version {string} cuya descripcion es {string}")
	public void ingreso_de_para_el_cliente_con_el_alcance_para_la_version_cuya_descripcion_es(String unNombreDeProyecto, String unTipoDeProyecto, String unClienteDelProyecto, String unAlcanceDeProyecto, String unaVersionDelProducto, String unaDescripcionDelProyecto) {
	    proyecto = crearProyecto(unNombreDeProyecto, unTipoDeProyecto, unClienteDelProyecto, unAlcanceDeProyecto, unaVersionDelProducto, unaDescripcionDelProyecto);
	}

	private Proyecto crearProyecto(String unNombreDeProyecto, String unTipoDeProyecto, String unClienteDelProyecto,
			String unAlcanceDeProyecto, String unaVersionDelProducto, String unaDescripcionDelProyecto) {
		Proyecto unProyecto = proyectoService.crearProyecto(new Proyecto(unNombreDeProyecto, unTipoDeProyecto, unClienteDelProyecto, unAlcanceDeProyecto, unaVersionDelProducto, unaDescripcionDelProyecto));
		return unProyecto;
	}
	
	@Then("el proyecto es creado con el nombre {string} de {string} para el cliente {string} con el alcance {string} para la version {string} cuya descripcion es {string} y su estado es {string}")
	public void testComprobarElProyectoEsCreado(String unNombreDeProyecto, String unTipoDeProyecto, String unClienteDelProyecto, String unAlcanceDeProyecto, String unaVersionDelProducto, String unaDescripcionDelProyecto, String unEstadoDeProyecto) {
	    Optional<Proyecto> proyectoPruebaOp = proyectoService.buscarPorID(proyecto.getid());
	    if (proyectoPruebaOp.isPresent()) {
	    	Proyecto proyectoPrueba = proyectoPruebaOp.get();
	    	assertEquals(proyectoPrueba.getNombre(), unNombreDeProyecto);
	    	assertEquals(proyectoPrueba.getCliente(), unClienteDelProyecto);
	    	assertEquals(proyectoPrueba.getAlcance(), unAlcanceDeProyecto);
	    	assertEquals(proyectoPrueba.getVersion(), unaVersionDelProducto);
	    	assertEquals(proyectoPrueba.getDescripcion(), unaDescripcionDelProyecto);
	    	assertEquals(proyectoPrueba.getEstado(), "Pendiente");
	    }
	    
	    
	}

}
