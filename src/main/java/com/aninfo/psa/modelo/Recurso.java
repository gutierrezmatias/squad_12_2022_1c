package com.aninfo.psa.modelo;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(hidden = true)
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
