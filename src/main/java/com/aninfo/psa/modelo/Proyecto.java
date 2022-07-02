package com.aninfo.psa.modelo;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
    @Schema(hidden = true)
    private List<Tarea> tareas = new ArrayList<Tarea>();

    /*@OneToMany(cascade = {CascadeType.ALL})
    @ApiModelProperty(hidden = true, required = false)
    private List<Recurso> recursos = new ArrayList<Recurso>();*/
    private int horaEstimada;

    @Schema(hidden = true)
    private String fecha_inicio;

    private int fecha_fin;

    @Schema(example = "En curso")
    private String estado = "Pendiente";
    @OneToOne(cascade = {CascadeType.ALL})
    @Schema(required = true)
    private Recurso lider;


    public Proyecto(String nombre, String tipo, String cliente, String alcance, String version, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cliente = cliente;
        this.alcance = alcance;
        this.version = version;
        this.descripcion = descripcion;
    }
   
    public Proyecto(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yy");
        fecha_inicio = formatter.format(date);
    }

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
        for(Tarea tareaAsignada : tareas){
            tareaAsignada.eliminar();
        }
    }

    public void asignar_lider(Recurso recurso) {
        if(estado.equals("Pendiente") || estado.equals("En curso")){
            this.lider = recurso;
            this.estado = "En curso";
        }
    }

    public Recurso getLider() {
        return this.lider;
    }

    public void add_tarea(Tarea tarea) {
        if ((estado.equals("Pendiente") || estado.equals("En curso"))
            && (tarea.getEstado().equals("Pendiente")))
            tareas.add(tarea);
    }

    public List<Tarea> getTareas() {
        return this.tareas;
    }

    public List<Recurso> lista_Recursos(){
        if (this.tareas == null){
            return new ArrayList<Recurso>();
        }
        return this.tareas.stream().map(Tarea::getRecursoAsignado).collect(Collectors.toList());
    }

    public Long getid(){
        return this.id;
    }

    public int getHoraEstimada(){return this.horaEstimada;}

    public String getFecha_inicio(){return this.fecha_inicio;}

    public int getFecha_fin(){return this.fecha_fin;}
    public List<Tarea> buscar_tarea_por_estado(String arg0) {

        List<Tarea> conEstado = tareas.stream()
                .filter(tarea -> tarea.getEstado().equals(arg0))
                .collect(Collectors.toList());
        return new ArrayList<Tarea>(conEstado);
    }

    public List<Tarea> buscar_tarea_por_prioridad(String arg0) {

        List<Tarea> conPrioridad = tareas.stream()
                .filter(tarea -> tarea.getPrioridad().equals(arg0))
                .collect(Collectors.toList());
        return new ArrayList<Tarea>(conPrioridad);
    }

    public void remover_tarea(Long tarea_id) {
        tareas.removeIf(tarea -> tarea.getId().equals(tarea_id));
    }

    public void actualizar(ProyectoPatch proyecto1) {
        this.nombre = (proyecto1.getNombre() == null || proyecto1.getNombre().isEmpty())  ? this.nombre : proyecto1.getNombre();
        this.descripcion = (proyecto1.getDescripcion() == null || proyecto1.getDescripcion().isEmpty()) ? this.descripcion : proyecto1.getDescripcion();
        this.alcance = (proyecto1.getAlcance() == null || proyecto1.getAlcance().isEmpty()) ? this.alcance : proyecto1.getAlcance();
        this.estado = (proyecto1.getEstado() == null || proyecto1.getEstado().isEmpty()) ? this.estado : proyecto1.getEstado();
        this.version = (proyecto1.getVersion() == null || proyecto1.getVersion().isEmpty()) ? this.version : proyecto1.getVersion();
        this.cliente = (proyecto1.getCliente() == null || proyecto1.getCliente().isEmpty()) ? this.cliente : proyecto1.getCliente();
        this.lider = (proyecto1.getLider() == null) ? this.lider : proyecto1.getLider();
        this.tipo = (proyecto1.getTipo() == null || proyecto1.getTipo().isEmpty()) ? this.tipo : proyecto1.getTipo();
        this.fecha_fin = (proyecto1.getFecha_fin() == null ) ? this.fecha_fin : proyecto1.getFecha_fin();

    }

    public void recalcular_horas_estimadas() {
        this.horaEstimada = this.getTareas().stream().mapToInt(tarea -> tarea.getHorasEstimadas()).sum();
    }

	public void borrarTarea(String unNombre) {
		if (estado.equals("En curso") || estado.equals("Pendiente")) {
			for (Tarea unaTarea: tareas) {
				if (unaTarea.getNombre().equals(unNombre) && (unaTarea.getEstado().equals("En curso") || unaTarea.getEstado().equals("Pendiente")))
                    unaTarea.eliminar();
			}
		}
	}

	public boolean existeTarea(String unNombre) {
		for (Tarea unaTarea: tareas) {
			if (unaTarea.getNombre().equals(unNombre)) return true;
		}
		return false;
	}
	
	public void finalizar() {
		this.estado = "Finalizado";
		for (Tarea unaTarea: tareas) {
			unaTarea.finalizar();
		}
	}

	public Tarea getTarea(String unNombreDeTarea) {
		for (Tarea unaTarea: tareas) {
			if (unaTarea.getNombre().equals(unNombreDeTarea)) return unaTarea;
		}
		return null;
	}

	public void setEstado(String unEstado) {
		estado = unEstado;
	}
}
