package com.aninfo.psa.listas;

import com.aninfo.psa.modelo.Tarea;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ListaDeTareas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Tarea> tareas = new ArrayList<Tarea>();
    public void add(Tarea tarea) {
        tareas.add(tarea);
    }

    public List<Tarea> get_tareas() {
        return tareas;
    }

    public List<Tarea> buscar_estado(String arg0) {
        List<Tarea> conEstado = tareas.stream()
                .filter(tarea -> tarea.getEstado().equals(arg0))
                .collect(Collectors.toList());
        return new ArrayList<Tarea>(conEstado);
    }

    public List<Tarea> buscar_prioridad(String arg0) {
        List<Tarea> conPrioridad = tareas.stream()
                .filter(tarea -> tarea.getPrioridad().equals(arg0))
                .collect(Collectors.toList());
        return new ArrayList<Tarea>(conPrioridad);
    }
}
