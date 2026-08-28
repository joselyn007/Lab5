package com.lab.apis.controller;

import com.lab.apis.model.Libros;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    private List<Libros> libros = new ArrayList<>();

    public LibrosController() {
        libros.add(new Libros(1L, "Cien Años de Soledad", "Gabriel García Márquez", 1967, 150.0));
        libros.add(new Libros(2L, "Don Quijote de la Mancha", "Miguel de Cervantes", 1605, 119.0));
        libros.add(new Libros(3L, "El Principito", "Antoine de Saint-Exupéry", 1943, 30.0));
        libros.add(new Libros(4L, "1984", "George Orwell", 1949, 20.0));
        libros.add(new Libros(5L, "El chico de las estrellas", "Chris Pueyo", 2015, 150.0));
    }

    @GetMapping
    public List<Libros> obtenerTodos() {
        return libros;
    }

    @GetMapping("/{id}")
    public Libros obtenerPorId(@PathVariable Long id) {
        return libros.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Libros crear(@RequestBody Libros nuevo) {
        libros.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Libros actualizar(@PathVariable Long id, @RequestBody Libros actualizado) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId().equals(id)) {
                actualizado.setId(id);
                libros.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Libros actualizarParcial(@PathVariable Long id, @RequestBody Libros cambios) {
    Libros existente = obtenerPorId(id);
    if (existente != null) {
        if (cambios.getTitulo() != null) existente.setTitulo(cambios.getTitulo());
        if (cambios.getAutor() != null) existente.setAutor(cambios.getAutor());
        if (cambios.getAñopub() != null) existente.setAñopub(cambios.getAñopub());
        if (cambios.getPrecio() != null) existente.setPrecio(cambios.getPrecio());
    }
    return existente;
}

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        libros.removeIf(e -> e.getId().equals(id));
        return "El libro ha sido eliminado correctamente";
    }
    
}
