package com.aninfo.psa.listas;

import com.aninfo.psa.modelo.Proyecto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ListaDeProyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    List<Proyecto> lista = new ArrayList<Proyecto>();
    public void add(Proyecto proyecto1) {
        this.lista.add(proyecto1);
    }

    public ArrayList<Proyecto> listado_proyectos() {
        List ordenada = lista.stream()
                .sorted(((o1, o2) -> o1.getEstado().compareTo(o2.getEstado())))
                .collect(Collectors.toList());

        return new ArrayList<Proyecto>(ordenada);

    }
}
