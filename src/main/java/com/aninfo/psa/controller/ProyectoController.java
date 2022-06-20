package com.aninfo.psa.controller;

import com.aninfo.psa.Repository.ProyectosRepository;
import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.excepciones.ErrorNoExisteElProyectoParaAsignar;
import com.aninfo.psa.excepciones.NoExisteLaTareaBuscadaError;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;
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

    @PutMapping("/proyectos/{id}/tareas")
    public Tarea asignar_tarea(@PathVariable Long id, Long tarea_id){
        Optional<Proyecto> Optionalproyecto = proyectoService.buscarPorNombre(id);
        if (!Optionalproyecto.isPresent()){
            throw new ErrorNoExisteElProyectoParaAsignar();
        }
        return proyectoService.asignar_tarea(Optionalproyecto.get(), tarea_id);
    }

    @DeleteMapping("/proyectos/{id}/tareas")
    public void eliminar_tarea(@PathVariable Long id, Long tarea_id){
        Optional<Proyecto> Optionalproyecto = proyectoService.buscarPorNombre(id);
        if (!Optionalproyecto.isPresent()){
            throw new ErrorNoExisteElProyectoParaAsignar();
        }
        proyectoService.eliminar_tarea(id, tarea_id);
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
