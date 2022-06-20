/*package com.aninfo.psa.controller;

import com.aninfo.psa.Repository.TareasRepository;
import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.excepciones.ErrorNoExisteElProyectoParaAsignar;
import com.aninfo.psa.excepciones.NoExisteLaTareaBuscadaError;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TareaController {

    @Autowired
    private TareaService tareaService;
    @Autowired
    private ProyectoService proyectoService;

    TareaController(){
    }

    @PostMapping("/tareas")
    public Tarea crear_tarea(@RequestBody Tarea tarea){
        return tareaService.crear_tarea(tarea);
    }

    @GetMapping("/tareas")
    public List<Tarea> get_tareas_asignadas(){
        return tareaService.obtener_tareas();
    }

    @DeleteMapping("/tareas/{id}")
    public void delete_tarea(@PathVariable Long id){
        tareaService.deleteByid(id);
    }
    @GetMapping("/tareas/{id}")
    public ResponseEntity<Tarea> get_tarea(@PathVariable Long id) {
        Optional<Tarea> Optionaltarea = tareaService.obtener_tarea(id);
        if (!Optionaltarea.isPresent()) {
            throw new NoExisteLaTareaBuscadaError();
        }
        return ResponseEntity.of(Optionaltarea);
    }
    /*
    @GetMapping("/proyectos/{id}/tareas")
    public List<Tarea> get_tareas_asignadas_al_proyecto(@PathVariable Long id){
        return proyectoService.get_tareas(id);
    }



    @PutMapping("/proyectos/{id}/tareas")
    public Tarea asignar_tarea(@PathVariable Long id, @RequestBody Tarea tarea){
        Optional<Proyecto> proyectoOptional= proyectoService.buscarPorNombre(id);
        if (!proyectoOptional.isPresent()){
            throw new ErrorNoExisteElProyectoParaAsignar();
        }
        return proyectoService.asignar_tarea(proyectoOptional.get(),tarea);
    }

     */


