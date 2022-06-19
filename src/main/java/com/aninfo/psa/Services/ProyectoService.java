package com.aninfo.psa.Services;

import com.aninfo.psa.Repository.ProyectosRepository;
import com.aninfo.psa.Repository.TareasRepository;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Tarea asignar_tarea(Proyecto proyecto, Tarea tarea) {
        proyecto.add_tarea(tarea);
        return tareasRepository.save(tarea);
    }
}
