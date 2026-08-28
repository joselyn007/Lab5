package com.lab.apis.model;

public class Pedidos {
    private Long id;
    private String cliente;
    private String producto;
    private Integer cantidad;
    private Double total;
    private String estado;

    public Pedidos() {}

    public Pedidos(Long id, String cliente, String producto, Integer cantidad, Double total, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
        this.estado = estado;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getCliente() {
        return cliente;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    public String getProducto() {
        return producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Integer getCantidad() {
        return cantidad;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public Double getTotal() {
        return total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getEstado() {
        return estado;
    }
}