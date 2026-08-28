package com.lab.apis.model;

public class Vehiculos {
    private Long id;
    private String marca;
    private String modelo;
    private Integer año;
    private Double precio;

    public Vehiculos() {}

    public Vehiculos(Long id, String marca, String modelo, Integer año, Double precio){
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.precio = precio;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getModelo() {
        return modelo;
    }

    public void setAño(Integer año) {
        this.año = año;
    }
    public Integer getAño() {
        return año;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Double getPrecio() {
        return precio;
    }

}
