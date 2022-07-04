package com.aninfo.psa.Services;

import com.aninfo.psa.Repository.ProyectosRepository;
import com.aninfo.psa.Repository.TareasRepository;
import com.aninfo.psa.excepciones.LaTareaTieneAsignadaUnProyecto;
import com.aninfo.psa.excepciones.NoExisteElProyectoBuscadoError;
import com.aninfo.psa.excepciones.NoExisteLaTareaBuscadaError;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class TareaService {

    @Autowired
    private TareasRepository tareasRepository;

    @Autowired
    private ProyectosRepository proyectosRepository;

    public List<Tarea> obtener_tareas() {
        return tareasRepository.findAll();
    }

    @Transactional
    public Optional<Tarea> obtener_tarea(Long id) {
        return tareasRepository.findById(id);
    }

    @Transactional
    public Tarea crear_tarea(Tarea tarea) {
        Tarea respuesta = tareasRepository.save(tarea);
        return respuesta;
    }

    public void deleteByid(Long id) {
        if(tareasRepository.findById(id).get().getProyectoID() != null){
            throw new LaTareaTieneAsignadaUnProyecto();
        }
        tareasRepository.deleteById(id);
    }

    @Transactional
	public void eliminarTarea(Long id) {
		Optional<Tarea> tareaAEliminar = tareasRepository.findById(id);
        Optional<Proyecto> proyectoOptional = proyectosRepository.findById(tareaAEliminar.get().getProyectoID());
        if (proyectoOptional.isPresent()){
            proyectoOptional.get().remover_tarea(id);
        }
		if (tareaAEliminar.isPresent()) {
			tareaAEliminar.get().setEstado("Eliminada");
            tareaAEliminar.get().actualizar_proyecto_id(null);
			tareasRepository.save(tareaAEliminar.get());
		}
		
	}

    @Transactional
	public void finalizar(Long id) {
    	Optional<Tarea> tareaAFinalizar = tareasRepository.findById(id);
		if (tareaAFinalizar.isPresent() && tareaAFinalizar.get().getEstado() != "Pendiente" && tareaAFinalizar.get().getEstado() != "Finalizada") {
			tareaAFinalizar.get().finalizar();
			tareasRepository.save(tareaAFinalizar.get());
		}
		
	}
    
    @Transactional
	public void asignar_estado(Long id, String unEstado) {
		Optional<Tarea> tareaACambiarEstado = tareasRepository.findById(id);
		if (tareaACambiarEstado.isPresent()) {
			tareaACambiarEstado.get().setEstado(unEstado);
			tareasRepository.save(tareaACambiarEstado.get());
		}
	}


    public void initialize() {
    }

    public Tarea actualizar_tarea(Optional<Tarea> optionalTarea, Tarea tarea) {
       optionalTarea.get().actualizar(tarea);
       return tareasRepository.save(optionalTarea.get());

    }
}
