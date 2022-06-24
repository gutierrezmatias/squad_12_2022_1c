package com.aninfo.psa.modelo;

import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApiIgnore
@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;

    private String cliente;
    private String alcance;
    private String version;
    private String descripcion;
    @OneToMany(cascade = {CascadeType.DETACH})
    @ApiModelProperty(hidden = true)
    private List<Tarea> tareas = new ArrayList<Tarea>();

    /*@OneToMany(cascade = {CascadeType.ALL})
    @ApiModelProperty(hidden = true, required = false)
    private List<Recurso> recursos = new ArrayList<Recurso>();*/
    private int horaEstimada;

    private int fecha_inicio;

    private int fecha_fin;
    private String estado = "Activo";
    @OneToOne(cascade = {CascadeType.ALL})
    @ApiModelProperty(required = true)
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

    public List<Recurso> lista_Recursos(){
        if (this.tareas == null){
            return new ArrayList<Recurso>();
        }
        return this.tareas.stream().map(tarea -> tarea.getRecursoAsignado()).collect(Collectors.toList());
    }

    public long getid(){
        return this.id;
    }

    public int getHoraEstimada(){return this.horaEstimada;}

    public int getFecha_inicio(){return this.fecha_inicio;}

    public int getFecha_fin(){return this.fecha_fin;}
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

    public void remover_tarea(Long tarea_id) {
        tareas.removeIf(tarea -> tarea.getId().equals(tarea_id));
    }

    public void actualizar(ProyectoPatch proyecto1) {
        this.nombre = (proyecto1.getNombre() == null) ? this.nombre : proyecto1.getNombre();
        this.descripcion = (proyecto1.getDescripcion() == null) ? this.descripcion : proyecto1.getDescripcion();
        this.alcance = (proyecto1.getAlcance() == null) ? this.alcance : proyecto1.getAlcance();
        this.estado = (proyecto1.getEstado() == null) ? this.estado : proyecto1.getEstado();
        this.version = (proyecto1.getVersion() == null) ? this.version : proyecto1.getVersion();
        this.cliente = (proyecto1.getCliente() == null) ? this.cliente : proyecto1.getCliente();
        this.lider = (proyecto1.getLider() == null) ? this.lider : proyecto1.getLider();
        this.fecha_fin = (proyecto1.getFecha_fin() == null) ? this.fecha_fin : proyecto1.getFecha_fin();

    }

    public void recalcular_horas_estimadas() {
        this.horaEstimada = this.getTareas().stream().mapToInt(tarea -> tarea.gethorasEstimadas()).sum();
    }

	public void borrarTarea(String unNombre) {
		for (Tarea unaTarea: tareas) {
			if (unaTarea.getNombre().equals(unNombre)) tareas.remove(unaTarea);
		}
		
	}

	public boolean existeTarea(String unNombre) {
		for (Tarea unaTarea: tareas) {
			if (unaTarea.getNombre().equals(unNombre)) return true;
		}
		
		return false;
		
	}
}
