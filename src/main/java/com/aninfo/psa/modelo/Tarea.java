package com.aninfo.psa.modelo;


import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;

@ApiIgnore
@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int horasEstimadas;
    private String nombre;
    private int fechaCreacion;
    @OneToOne(cascade = {CascadeType.ALL})
    private Recurso recursoAsignado;
    private String estado;
    private String prioridad;
    private String descripcion;
    private String objetivo;


    @ApiModelProperty(hidden = true)
    private Long ProyectoID;

    public Tarea(String unNombre, int unaFecha, String unaDescripcion, String unObjetivo){
      nombre = unNombre;
      fechaCreacion = unaFecha;
      descripcion = unaDescripcion;
      objetivo = unObjetivo;

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

    public int getFechaCreacion(){return this.fechaCreacion;}

    public Recurso getRecursoAsignado(){return this.recursoAsignado;}

    public Long getProyectoID(){return this.ProyectoID;}

    public void actualizar_proyecto_id(Long getid) {
        this.ProyectoID = getid;
    }
}
