package com.aninfo.psa.Services;

import com.aninfo.psa.Repository.ProyectosRepository;
import com.aninfo.psa.Repository.TareasRepository;
import com.aninfo.psa.excepciones.NoExisteLaTareaBuscadaError;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.ProyectoPatch;
import com.aninfo.psa.modelo.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {
    @Autowired
    private ProyectosRepository proyectosRepository;

    @Autowired
    private TareasRepository tareasRepository;

    public Proyecto crearProyecto(Proyecto proyecto){
        return proyectosRepository.save(proyecto);
    }

    public Collection<Proyecto> obtenerProyectos(){
        return proyectosRepository.findAll();
    }

    public Optional<Proyecto> buscarPorNombre(Long id){
        return proyectosRepository.findById(id);
    }

    public void save(Proyecto proyecto){
        proyectosRepository.save(proyecto);
    }

    public void deleteById(Long id){
        proyectosRepository.deleteById(id);
    }

    public List<Tarea> get_tareas(Long id) {
        return this.buscarPorNombre(id).get().getTareas();
    }

    @Transactional
    public Tarea asignar_tarea(Proyecto proyecto, Long id) {
        Optional<Tarea> optionalTarea = tareasRepository.findById(id);
        if (!optionalTarea.isPresent()){
            throw new NoExisteLaTareaBuscadaError();
        }
        optionalTarea.get().actualizar_proyecto_id(proyecto.getid());
        proyecto.add_tarea(optionalTarea.get());
        proyecto.recalcular_horas_estimadas();
        return optionalTarea.get();
    }
    @Transactional
    public void eliminar_tarea(Long id, Long tarea_id) {
        var proyecto = proyectosRepository.findById(id).get();
        proyecto.remover_tarea(tarea_id);
        proyecto.recalcular_horas_estimadas();
        tareasRepository.findById(tarea_id).get().actualizar_proyecto_id(null);
    }

    @Transactional
    public Proyecto actualizar_proyecto(Proyecto proyecto, ProyectoPatch proyecto1) {
        proyecto.actualizar(proyecto1);
        proyectosRepository.save(proyecto);

        return proyecto;
    }
}
