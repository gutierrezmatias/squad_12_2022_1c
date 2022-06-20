package com.aninfo.psa.modelo;

import javax.persistence.*;

@Entity
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Long getID(){
        return this.id;
    }
    public Recurso(){}
    public Recurso(String name) {
        this.name = name;
    }

    public String getNombre() {
        return this.name;
    }
}
