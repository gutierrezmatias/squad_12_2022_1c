package com.aninfo.psa.modelo;

import io.swagger.annotations.ApiModelProperty;

public class ProyectoPatch {
    @ApiModelProperty(required = false)
    private String alcance;
    @ApiModelProperty(required = false)
    private String estado;
    @ApiModelProperty(required = false)
    private String nombre;
    @ApiModelProperty(required = false)
    private String version;
    @ApiModelProperty(required = false)
    private String descripcion;
    @ApiModelProperty(required = false)
    private String cliente;
    @ApiModelProperty(required = false)
    private Recurso lider;
    @ApiModelProperty(required = false)
    private Integer fecha_fin;

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

    public Integer getFecha_fin() {
        return this.fecha_fin;
    }
}
