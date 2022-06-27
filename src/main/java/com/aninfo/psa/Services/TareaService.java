package com.aninfo.psa.Services;

import com.aninfo.psa.Repository.ProyectosRepository;
import com.aninfo.psa.Repository.TareasRepository;
import com.aninfo.psa.excepciones.LaTareaTieneAsignadaUnProyecto;
import com.aninfo.psa.excepciones.NoExisteLaTareaBuscadaError;
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

    public Optional<Tarea> obtener_tarea(Long id) {
        return tareasRepository.findById(id);
    }

    public Tarea crear_tarea(Tarea tarea) {
        return tareasRepository.save(tarea);
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
		if (tareaAEliminar.isPresent()) {
			tareaAEliminar.get().setEstado("Eliminada");
			tareasRepository.save(tareaAEliminar.get());
		}
		
	}

    @Transactional
	public void finalizar(Long id) {
    	Optional<Tarea> tareaAFinalizar = tareasRepository.findById(id);
		if (tareaAFinalizar.isPresent()) {
			tareaAFinalizar.get().setEstado("Finalizada");
			tareasRepository.save(tareaAFinalizar.get());
		}
		
	}
}
