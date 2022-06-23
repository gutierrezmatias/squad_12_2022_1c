package com.aninfo.psa.modelo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    private String name;



    public Long getID(){
        return this.id;
    }
    public Recurso(){}
    public Recurso(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
