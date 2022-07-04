package com.aninfo.psa.modelo;


import io.swagger.v3.oas.annotations.media.Schema;

public class ProyectoPatch {
    @Schema(required = false)
    private String alcance;
    @Schema(required = false)
    private String estado;
    @Schema(required = false)
    private String nombre;
    @Schema(required = false)
    private String version;
    @Schema(required = false)
    private String descripcion;
    @Schema(required = false)
    private String cliente;
    @Schema(required = false)
    private Recurso lider;
    @Schema(required = false)
    private String fecha_fin;

    @Schema(required = false)
    private String tipo;

    private String fecha_inicio;

    public ProyectoPatch(){}

    public String getAlcance(){return this.alcance;}
    public String getEstado(){return this.estado;}
    public String getNombre(){return this.nombre;}
    public String getVersion(){return this.version;}
    public String getDescripcion(){return this.descripcion;}
    public String getCliente(){return this.cliente;}

    public Recurso getLider() {
        return this.lider;
    }

    public String getFecha_fin() {
        return this.fecha_fin;
    }

    public String getTipo(){return this.tipo;}

    public String getFecha_inicio(){return this.fecha_inicio;}
}
