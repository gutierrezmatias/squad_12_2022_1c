package com.aninfo.psa.modelo;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(hidden = true)
    private Long id;


    private String name;

    private Long id_recurso;

    public Recurso(){}
    public Recurso(String name) {
        this.name = name;
    }

    public Recurso(Long id){
        this.id = id;
    }

    public Long getId_recurso(){return this.id_recurso;}
    public String getName() {
        return this.name;
    }
}
