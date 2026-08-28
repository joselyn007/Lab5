package com.lab.apis.model;

public class Tareas {
    private Long id;
    private String titulo;
    private String descripcion;
    private String prioridad;
    private String completada;

    public Tareas() {}

    public Tareas(Long id, String titulo, String descripcion, String prioridad, String completada) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.completada = completada;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    public String getPrioridad() {
        return prioridad;
    }

    public void setCompletada(String completada) {
        this.completada = completada;
    }
    public String getCompletada() {
        return completada;
    }
}