package com.lab.apis.model;

public class Libros {
    private Long id;
    private String titulo;
    private String autor;
    private Integer añopub;
    private Double precio;

    public Libros(Long id, String titulo, String autor, Integer añopub, Double precio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.añopub = añopub;
        this.precio = precio;
    }
// ------------------------------------------------------------
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
// ------------------------------------------------------------
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTitulo() {
        return titulo;
    }
// ------------------------------------------------------------
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getAutor() {
        return autor;
    }
// ------------------------------------------------------------
    public void setAñopub(Integer añopub) {
        this.añopub = añopub;
    }
    public Integer getAñopub() {
        return añopub;
    }
// ------------------------------------------------------------
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Double getPrecio() {
        return precio;
    }

}
