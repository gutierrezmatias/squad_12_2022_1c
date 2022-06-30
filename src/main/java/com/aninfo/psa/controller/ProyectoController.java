package com.aninfo.psa.controller;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.excepciones.ErrorNoExisteElProyectoParaAsignar;
import com.aninfo.psa.excepciones.NoExisteElProyectoBuscadoError;
import com.aninfo.psa.excepciones.NoExisteElProyectoParaActualizar;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.ProyectoPatch;
import com.aninfo.psa.modelo.Recurso;
import com.aninfo.psa.modelo.Tarea;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "Crear un proyecto")
    @ApiResponses(value = {
            @ApiResponse(responseCode =  "200", description = "Successfully Created")
    })
    @PostMapping("/proyectos")
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto){

        return proyectoService.crearProyecto(proyecto);
    }

    @Operation(summary = "Obtener todos los proyectos creados")
    @GetMapping("/proyectos")
    public Collection<Proyecto> getProyectos(){
        return proyectoService.obtenerProyectos();
    }

    @Operation(summary = "Obtener un proyecto por id")
    @GetMapping("/proyectos/{id}")
    public ResponseEntity<Proyecto> get_proyecto(@PathVariable Long id){
        Optional<Proyecto> proyectoOptional = proyectoService.buscarPorID(id);
        return ResponseEntity.of(proyectoOptional);
    }

    @Operation(summary = "Eliminar un proyecto por id")
    @DeleteMapping("/proyectos/{id}")
    public void delete_proyecto(@PathVariable Long id){
        proyectoService.deleteById(id);
    }
    
    @Operation(summary = "Finalizar una tarea por id")
    @PatchMapping("/proyectos/{id}")
    public void finalizar_tarea(@PathVariable Long proyectoId){
       proyectoService.finalizar(proyectoId);
    }

    @Operation(summary = "Actualizar un proyecto por id")
    @PatchMapping("/proyectos/{id}")
    public ResponseEntity<Proyecto> actualizar_proyecto(@PathVariable Long id, @RequestBody ProyectoPatch proyecto){
        Optional<Proyecto> optionalProyecto = proyectoService.buscarPorID(id);
        if (!optionalProyecto.isPresent()){
            throw new NoExisteElProyectoParaActualizar();
        }
        return ResponseEntity.ok(proyectoService.actualizar_proyecto(optionalProyecto.get(),proyecto));
    }

    @Operation(summary = "Obtener todos los recursos asignados a un proyecto")
    @GetMapping("/proyectos/{id}/recursos")
    public List<Recurso> get_recursos_asignados(@PathVariable Long id){
        Optional<Proyecto> optionalProyecto = proyectoService.buscarPorID(id);
        if (!optionalProyecto.isPresent()){
            throw new NoExisteElProyectoBuscadoError();
        }
        return optionalProyecto.get().lista_Recursos();
    }

    @Operation(summary = "Asignar una tarea a un proyecto")
    @PutMapping("/proyectos/{id}/tareas")
    public Tarea asignar_tarea(@PathVariable Long id, Long tarea_id){
        Optional<Proyecto> Optionalproyecto = proyectoService.buscarPorID(id);
        if (!Optionalproyecto.isPresent()){
            throw new ErrorNoExisteElProyectoParaAsignar();
        }
        return proyectoService.asignar_tarea(Optionalproyecto.get(), tarea_id);
    }

    @Operation(summary = "Eliminar una tarea de un proyecto por id")
    @DeleteMapping("/proyectos/{id}/tareas")
    public void eliminar_tarea(@PathVariable Long id, Long tarea_id){
        Optional<Proyecto> Optionalproyecto = proyectoService.buscarPorID(id);
        if (!Optionalproyecto.isPresent()){
            throw new ErrorNoExisteElProyectoParaAsignar();
        }
        proyectoService.eliminar_tarea(id, tarea_id);
    }

    @GetMapping("/proyectos/obtener_por_nombre")
    public List<Proyecto> obtener_proyectos_por_nombre(String nombre){
        return proyectoService.BuscarPorNombre(nombre);
    }

}