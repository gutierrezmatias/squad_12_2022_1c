package com.aninfo.psa.controller;

import com.aninfo.psa.Repository.ProyectosRepository;
import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.excepciones.ErrorNoExisteElProyectoParaAsignar;
import com.aninfo.psa.excepciones.NoExisteElProyectoBuscadoError;
import com.aninfo.psa.excepciones.NoExisteElProyectoParaActualizar;
import com.aninfo.psa.excepciones.NoExisteLaTareaBuscadaError;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.ProyectoPatch;
import com.aninfo.psa.modelo.Recurso;
import com.aninfo.psa.modelo.Tarea;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class ProyectoController {
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private TareaService tareaService;

    ProyectoController(){
    }


    @ApiOperation(value = "Crear un proyecto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Created")
    })
    @PostMapping("/proyectos")
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto){
        return proyectoService.crearProyecto(proyecto);
    }

    @ApiOperation(value = "Obtener todos los proyectos creados")
    @GetMapping("/proyectos")
    public Collection<Proyecto> getProyectos(){
        return proyectoService.obtenerProyectos();
    }

    @ApiOperation(value = "Obtener un proyecto por id")
    @GetMapping("/proyectos/{id}")
    public ResponseEntity<Proyecto> get_proyecto(@PathVariable Long id){
        Optional<Proyecto> proyectoOptional = proyectoService.buscarPorNombre(id);
        return ResponseEntity.of(proyectoOptional);
    }

    @ApiOperation(value = "Eliminar un proyecto por id")
    @DeleteMapping("/proyectos/{id}")
    public void delete_proyecto(@PathVariable Long id){
        proyectoService.deleteById(id);
    }

    @ApiOperation(value = "Actualizar un proyecto por id")
    @PatchMapping("/proyectos/{id}")
    public ResponseEntity<Proyecto> actualizar_proyecto(@PathVariable Long id, @RequestBody ProyectoPatch proyecto){
        Optional<Proyecto> optionalProyecto = proyectoService.buscarPorNombre(id);
        if (!optionalProyecto.isPresent()){
            throw new NoExisteElProyectoParaActualizar();
        }
        return ResponseEntity.ok(proyectoService.actualizar_proyecto(optionalProyecto.get(),proyecto));
    }

    @ApiOperation(value = "Obtener todos los recursos asignados a un proyecto")
    @GetMapping("/proyectos/{id}/recursos")
    public List<Recurso> get_recursos_asignados(@PathVariable Long id){
        Optional<Proyecto> optionalProyecto = proyectoService.buscarPorNombre(id);
        if (!optionalProyecto.isPresent()){
            throw new NoExisteElProyectoBuscadoError();
        }
        return optionalProyecto.get().lista_Recursos();
    }

    @ApiOperation(value = "Asignar una tarea a un proyecto")
    @PutMapping("/proyectos/{id}/tareas")
    public Tarea asignar_tarea(@PathVariable Long id, Long tarea_id){
        Optional<Proyecto> Optionalproyecto = proyectoService.buscarPorNombre(id);
        if (!Optionalproyecto.isPresent()){
            throw new ErrorNoExisteElProyectoParaAsignar();
        }
        return proyectoService.asignar_tarea(Optionalproyecto.get(), tarea_id);
    }

    @ApiOperation(value = "Eliminar una tarea de un proyecto por id")
    @DeleteMapping("/proyectos/{id}/tareas")
    public void eliminar_tarea(@PathVariable Long id, Long tarea_id){
        Optional<Proyecto> Optionalproyecto = proyectoService.buscarPorNombre(id);
        if (!Optionalproyecto.isPresent()){
            throw new ErrorNoExisteElProyectoParaAsignar();
        }
        proyectoService.eliminar_tarea(id, tarea_id);
    }

}
