package com.aninfo.psa.modelo;



import io.cucumber.java.bs.A;
import io.swagger.v3.oas.annotations.media.Schema;


import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;


@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int horasEstimadas;
    private String nombre;
    private String fechaCreacion;
    @OneToOne(cascade = {CascadeType.ALL})
    private Recurso recursoAsignado;
    private String estado;
    private String prioridad;
    private String descripcion;
    private String objetivo;
    private String ticketAsociado;

    private ArrayList<Recurso> recursosAsignados;

    @Schema(hidden = true)
    private Long ProyectoID;

    public Tarea(String unNombre, String unaDescripcion, String unObjetivo, String unaPrioridad){
      nombre = unNombre;
      
      Date date = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yy");
      fechaCreacion = formatter.format(date);
      
      descripcion = unaDescripcion;
      objetivo = unObjetivo;
      prioridad = unaPrioridad;
      estado = "Pendiente";
      recursosAsignados = new ArrayList<>();
    }
    
    public Tarea(int unaEstimacion) {
    	horasEstimadas = unaEstimacion;
    }
    public Tarea(){}

    public Long getId(){
        return this.id;
    }

    public Tarea(String arg0) {
        this.nombre = arg0;
    }

    public void ingresar_estimado(int arg0) {
        this.horasEstimadas = arg0;
    }

    public int gethorasEstimadas() {
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
        this.prioridad = prioridad;
    }

    public String getPrioridad() {
        return this.prioridad;
    }

    public String getFechaCreacion(){return this.fechaCreacion;}

    //usado en proyecto
    public Recurso getRecursoAsignado(){return this.recursoAsignado;}

    public Long getProyectoID(){return this.ProyectoID;}

    public void finalizar() {
        this.estado = "Finalizada";
    }

    public void eliminar() {
        this.estado = "Eliminada";
    }

    public void actualizar_proyecto_id(Object o) {
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
            if(estado.equals("Pendiente")) estado = "En curso";
        }
    }

    public ArrayList<Recurso> getRecursosAsignados() {
        return recursosAsignados;
    }
}
