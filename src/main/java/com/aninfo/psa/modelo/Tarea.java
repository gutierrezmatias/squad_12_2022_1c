package com.aninfo.psa.modelo;


import javax.persistence.*;

@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int horas;
    private String nombre;
    private String estado;
    private String prioridad;
    private String descripción;
    private String objetivo;

    public Tarea(int horas){
        this.horas = horas;
    }
    public Tarea(){}

    public Tarea(String arg0) {
        this.nombre = arg0;
    }

    public void ingresar_estimado(int arg0) {
        this.horas = arg0;
    }

    public int getHorasEstimadas() {
        return this.horas;
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
}
