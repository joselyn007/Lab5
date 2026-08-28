package com.lab.apis.model;

public class Pelicula {
    private Long  id;
    private String titulo;
    private String director;
    private String genero;
    private Integer añopub;

    public Pelicula(Long id, String titulo, String director, String genero, Integer añopub){
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.añopub = añopub;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }

    public void setTitulo(String titulo){
        this.titulo= titulo;
    }
    public String getTitulo(){
        return titulo;
    }

    public void setDirector(String director){
        this.director = director;
    }
    public String getDirector(){
        return director;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }
    public String getGenero(){
        return genero;
    }
    
    public void setAñopub(Integer añopub) {
        this.añopub = añopub;
    }
    public Integer getAñopub() {
        return añopub;
    }

}
