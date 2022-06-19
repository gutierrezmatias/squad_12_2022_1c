package com.aninfo.psa;

import com.aninfo.psa.excepciones.ErrorNoExisteElProyectoParaAsignar;
import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@EnableSwagger2
@SpringBootApplication()
@EnableOpenApi
public class PsaProyectosApplication {

	@Autowired
	ProyectoService proyectoService;

	public static void main(String[] args) {
		SpringApplication.run(PsaProyectosApplication.class, args);
	}

	@PostMapping("/proyectos")
	@ResponseStatus(HttpStatus.CREATED)
	public Proyecto crearProyecto(@RequestBody Proyecto proyecto){
		return proyectoService.crearProyecto(proyecto);
	}

	@GetMapping("/proyectos")
	public Collection<Proyecto> getProyectos(){
		return proyectoService.obtenerProyectos();
	}

	@GetMapping("/proyectos/{id}")
	public ResponseEntity<Proyecto> get_proyecto(@PathVariable Long id){
		Optional<Proyecto> proyectoOptional = proyectoService.buscarPorNombre(id);
		return ResponseEntity.of(proyectoOptional);
	}

	@DeleteMapping("/proyectos/{id}")
	public void delete_proyecto(@PathVariable Long id){
		proyectoService.deleteById(id);
	}

	@GetMapping("/proyectos/{id}/tareas")
	public List<Tarea> get_tareas_asignadas(@PathVariable Long id){
		return proyectoService.get_tareas(id);
	}



	@PutMapping("/proyectos/{id}/tareas")
	public Tarea asignar_tarea(@PathVariable Long id, @RequestBody Tarea tarea){
		Optional<Proyecto>  proyectoOptional= proyectoService.buscarPorNombre(id);
		if (!proyectoOptional.isPresent()){
			throw new ErrorNoExisteElProyectoParaAsignar();
		}
		return proyectoService.asignar_tarea(proyectoOptional.get(),tarea);
	}

}
