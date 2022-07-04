package com.aninfo.psa.modelo;



import io.swagger.v3.oas.annotations.media.Schema;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    private Long horasEstimadas;
    private String nombre;
    private String fechaCreacion;
    @OneToOne(cascade = {CascadeType.ALL})
    private Recurso recursoAsignado;

    @Schema(example = "Pendiente")
    private String estado = "Pendiente";
    private String prioridad = "baja";
    private String descripcion;
    private String objetivo;
    private String ticketAsociado;

    @Schema(hidden = true)
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Recurso> recursosAsignados = new ArrayList<Recurso>();

    private Long proyectoID;

    public Tarea(String unNombre, String unaDescripcion, String unObjetivo, String unaPrioridad){
      nombre = unNombre;
      
      Date date = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yy");
      fechaCreacion = formatter.format(date);
      
      descripcion = unaDescripcion;
      objetivo = unObjetivo;
      prioridad = unaPrioridad;
    }
    public Tarea(String unNombre, String unaDescripcion, String unObjetivo, String unaPrioridad, Long proyectoID){
        nombre = unNombre;

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yy");
        fechaCreacion = formatter.format(date);

        descripcion = unaDescripcion;
        objetivo = unObjetivo;
        prioridad = unaPrioridad;
        this.proyectoID = proyectoID;
    }
    
    public Tarea(int unaEstimacion) {
    	horasEstimadas = Long.valueOf(unaEstimacion);
    }
    public Tarea(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yy");
        fechaCreacion = formatter.format(date);
    }

    public Long getId(){
        return this.id;
    }

    public Tarea(String arg0) {
        this.nombre = arg0;
    }

    public void ingresar_estimado(int arg0) {

        if (estado.equals("Pendiente") || estado.equals("En curso")) this.horasEstimadas = Long.valueOf(arg0);
    }

    public Long getHorasEstimadas() {
        return this.horasEstimadas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void cambiar_estado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

    public void cambiar_prioridad(String prioridad) {

        if (estado.equals("Pendiente") || estado.equals("En curso")) this.prioridad = prioridad;
    }

    public String getPrioridad() {
        return this.prioridad;
    }

    public String getFechaCreacion(){return this.fechaCreacion;}

    //usado en proyecto
    public Recurso getRecursoAsignado(){return this.recursoAsignado;}

    public Long getProyectoID(){return this.proyectoID;}

    public void finalizar() {
        this.estado = "Finalizada";
    }

    public void eliminar() {
        this.estado = "Eliminada";
    }

    public void actualizar_proyecto_id(Long id) {
        this.proyectoID = id;

    }

    public String getObjetivo() {
        return this.objetivo;
    }

    public void setEstado(String unEstado) {
        this.estado = unEstado;
    }

    public void asignarResponsable(Recurso empleado){

        if(estado.equals("Pendiente") || estado.equals("En curso")) {
            recursosAsignados.add(empleado);
            estado = "En curso";
        }
    }

    public String getDescripcion(){return this.descripcion;}

    public List<Recurso> getRecursosAsignados() {
        return recursosAsignados;
    }

    public void actualizar(Tarea tarea) {
        this.nombre = (tarea.getNombre() == null || tarea.getNombre().isEmpty())  ? this.nombre : tarea.getNombre();
        this.descripcion = (tarea.getDescripcion() == null || tarea.getDescripcion().isEmpty()) ? this.descripcion : tarea.getDescripcion();
        this.estado = (tarea.getEstado() == null || tarea.getEstado().isEmpty()) ? this.estado : tarea.getEstado();
        this.prioridad = (tarea.getPrioridad() == null ||  tarea.getPrioridad().isEmpty()) ? this.prioridad : tarea.getObjetivo();
        this.recursoAsignado = (tarea.getRecursoAsignado() == null) ? this.recursoAsignado : tarea.getRecursoAsignado();
        this.horasEstimadas = (tarea.getHorasEstimadas() == null) ? this.horasEstimadas : tarea.getHorasEstimadas();
    }
}
