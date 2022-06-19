package com.aninfo.psa.modelo;

import com.aninfo.psa.listas.ListaDeTareas;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String tipo;
    private String cliente;
    private String alcance;
    private String version;

    private String testing;
    private String descripcion;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Tarea> tareas = new ArrayList<Tarea>();

    @ManyToMany
    private List<Recurso> recursos;
    private String horaEstimada;
    private String estado = "Activo";
    @ManyToOne(cascade = {CascadeType.ALL})
    private Recurso lider;


    public Proyecto(String nombre, String tipo, String cliente, String alcance, String version, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cliente = cliente;
        this.alcance = alcance;
        this.version = version;
        this.descripcion = descripcion;
    }

    public Proyecto(){}

    public Proyecto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getCliente() {
        return this.cliente;
    }

    public String getAlcance() {
        return this.alcance;
    }

    public String getVersion() {
        return this.version;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getEstado() {
        return this.estado;
    }

    public void dar_baja() {
        this.estado = "Interrumpido";
    }

    public void asignar_lider(Recurso recurso) {
        this.lider = recurso;
    }

    public Recurso getLider() {
        return this.lider;
    }

    public void add_tarea(Tarea tarea) {
        this.tareas.add(tarea);
    }

    public List<Tarea> getTareas() {
        return this.tareas;
    }

    public long getid(){
        return this.id;
    }

    public List<Tarea> buscar_tarea_por_estado(String arg0) {
        var conEstado = tareas.stream()
                .filter(tarea -> tarea.getEstado().equals(arg0))
                .collect(Collectors.toList());
        return new ArrayList<Tarea>(conEstado);
    }

    public List<Tarea> buscar_tarea_por_prioridad(String arg0) {
        var conPrioridad = tareas.stream()
                .filter(tarea -> tarea.getPrioridad().equals(arg0))
                .collect(Collectors.toList());
        return new ArrayList<Tarea>(conPrioridad);
    }
}
