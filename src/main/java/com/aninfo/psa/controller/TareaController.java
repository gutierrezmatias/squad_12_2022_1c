 package com.aninfo.psa.controller;

import com.aninfo.psa.Repository.TareasRepository;
import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.excepciones.ErrorNoExisteElProyectoParaAsignar;
import com.aninfo.psa.excepciones.NoExisteElProyectoParaActualizar;
import com.aninfo.psa.excepciones.NoExisteLaTareaBuscadaError;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.ProyectoPatch;
import com.aninfo.psa.modelo.Tarea;

import io.swagger.v3.oas.annotations.Operation;

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

    @DeleteMapping("/tareas/{tareaid}")
    public void delete_tarea(@PathVariable Long tareaid){
        tareaService.deleteByid(tareaid);
    }

    @GetMapping("/tareas/{tareaid}")
    public ResponseEntity<Tarea> get_tarea(@PathVariable Long tareaid) {
        Optional<Tarea> Optionaltarea = tareaService.obtener_tarea(tareaid);
        if (!Optionaltarea.isPresent()) {
            throw new NoExisteLaTareaBuscadaError();
        }
        return ResponseEntity.of(Optionaltarea);
    }
    
}


