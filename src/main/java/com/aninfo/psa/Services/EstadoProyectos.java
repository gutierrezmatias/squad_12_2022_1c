package com.aninfo.psa.Services;

public enum EstadoProyectos {
    EnCurso("En curso"), PENDIENTE("Pentiente"), ASIGNADO("Asignado"),FINALIZADO("Finalizado"), INTERRUMPIDO("Interrumpido");
    private String estado;

    EstadoProyectos(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return this.estado;
    }
}
