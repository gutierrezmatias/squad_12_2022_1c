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
import java.util.stream.Collectors;

@Service
public class ProyectoService {
    @Autowired
    private ProyectosRepository proyectosRepository;

    @Autowired
    private TareasRepository tareasRepository;
    
    @Autowired
    private TareaService tareaService;

    public Proyecto crearProyecto(Proyecto proyecto){
        return proyectosRepository.save(proyecto);
    }

    public Collection<Proyecto> obtenerProyectos(){
        return proyectosRepository.findAll();
    }

    public Optional<Proyecto> buscarPorID(Long id){
        return proyectosRepository.findById(id);
    }

    public void save(Proyecto proyecto){
        proyectosRepository.save(proyecto);
    }

    public void deleteById(Long id){
        proyectosRepository.deleteById(id);
    }

    public List<Tarea> get_tareas(Long id) {
        return this.buscarPorID(id).get().getTareas();
    }

    @Transactional
    public Tarea asignar_tarea(Proyecto proyecto, Long id) {
        Optional<Tarea> optionalTarea = tareasRepository.findById(id);

        if (!optionalTarea.isPresent()){
            throw new NoExisteLaTareaBuscadaError();
        }
        if (proyecto.getEstado().equals("Interrumpido")){
            return  optionalTarea.get();
        }
        if (optionalTarea.get().getProyectoID() == null) {
            Optional<Proyecto> proyecto_anterior = proyectosRepository.findById(optionalTarea.get().getProyectoID());

            if (proyecto_anterior.isPresent()) {
                if (proyecto_anterior.get().getEstado().equals("En curso") & !optionalTarea.get().getEstado().equals("Pendiente")) {
                    return optionalTarea.get();
                }
            }
        }
        optionalTarea.get().actualizar_proyecto_id(proyecto.getid());
        proyecto.add_tarea(optionalTarea.get());
        proyecto.recalcular_horas_estimadas();
        return optionalTarea.get();
    }
    
    @Transactional
    public void eliminar_tarea(Long id, Long tarea_id) {
        Proyecto proyecto = proyectosRepository.findById(id).get();
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

    public List<Proyecto> BuscarPorNombre(String nombre) {
        return proyectosRepository
                .findAll().stream()
                .filter(proyecto -> proyecto.getNombre().contains(nombre))
                .collect(Collectors.toList());
    }

    @Transactional
	public void finalizar(Long IdProyecto) {
    	
    	Optional<Proyecto> proyectoAFinalizarOp = proyectosRepository.findById(IdProyecto);
    	
    	if (proyectoAFinalizarOp.isPresent()) {
    		Proyecto proyectoAFinalizar = proyectoAFinalizarOp.get();
    		proyectoAFinalizar.setEstado("Finalizado");
    		for (Tarea unaTarea: proyectoAFinalizar.getTareas()) {
    			tareaService.finalizar(unaTarea.getId());
    		}
    				
    	}
		
	}

    @Transactional
	public void eliminarTarea(Long unIdDeProyecto, Long unIdDeTarea) {
		Optional<Proyecto> unProyectoOp = proyectosRepository.findById(unIdDeProyecto);
		
		if (unProyectoOp.isPresent()) {
			Proyecto proyectoAEliminarTarea = unProyectoOp.get();
			if (proyectoAEliminarTarea.getEstado() == "En curso" || proyectoAEliminarTarea.getEstado() == "Pendiente") {
				tareaService.eliminarTarea(unIdDeTarea);
				proyectosRepository.save(proyectoAEliminarTarea);
			}
	
		}
		
	}

    public void asignar_estado(Long valueOf, String arg1) {
        this.proyectosRepository.findById(valueOf).get().setEstado(arg1);
    }
}