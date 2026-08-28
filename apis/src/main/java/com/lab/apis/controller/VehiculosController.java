package com.lab.apis.controller;

import com.lab.apis.model.Vehiculos;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculosController {

    private List<Vehiculos> vehiculos = new ArrayList<>();

    public VehiculosController() {
        vehiculos.add(new Vehiculos(1L, "Toyota", "Corolla", 2022, 22000.0));
        vehiculos.add(new Vehiculos(2L, "Honda", "Civic", 2021, 24000.0));
        vehiculos.add(new Vehiculos(3L, "Ford", "Mustang", 2023, 45000.0));
        vehiculos.add(new Vehiculos(4L, "Chevrolet", "Onix", 2020, 15000.0));
        vehiculos.add(new Vehiculos(5L, "Nissan", "Sentra", 2022, 21000.0));
    }

    @GetMapping
    public List<Vehiculos> obtenerTodos() {
        return vehiculos;
    }

    @GetMapping("/{id}")
    public Vehiculos obtenerPorId(@PathVariable Long id) {
        return vehiculos.stream()
                .filter(v -> v.getId() != null && v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Vehiculos crear(@RequestBody Vehiculos nuevo) {
        vehiculos.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Vehiculos actualizar(@PathVariable Long id, @RequestBody Vehiculos actualizado) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getId() != null && vehiculos.get(i).getId().equals(id)) {
                actualizado.setId(id);
                vehiculos.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Vehiculos actualizarParcial(@PathVariable Long id, @RequestBody Vehiculos cambios) {
        Vehiculos existente = obtenerPorId(id);
        if (existente != null) {
            if (cambios.getMarca() != null) existente.setMarca(cambios.getMarca());
            if (cambios.getModelo() != null) existente.setModelo(cambios.getModelo());
            if (cambios.getAño() != null) existente.setAño(cambios.getAño());
            if (cambios.getPrecio() != null) existente.setPrecio(cambios.getPrecio());
        }
        return existente;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        vehiculos.removeIf(v -> v.getId() != null && v.getId().equals(id));
        return "El vehículo ha sido eliminado correctamente";
    }
}