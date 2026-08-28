package com.lab.apis.controller;

import com.lab.apis.model.Pelicula;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pelicula")
public class PeliculaController {

    private List<Pelicula> pelicula = new ArrayList<>();

    public PeliculaController() {
        pelicula.add(new Pelicula(1L, "Matrix", "Lana Wachowski", "Ciencia ficcion", 1999));
        pelicula.add(new Pelicula(2L, "Parasite", "Bong Joon-ho", "Drama", 2019 ));
        pelicula.add(new Pelicula(3L, "Psycho", "Alfred Hitchcock", "Terror", 1960));
        pelicula.add(new Pelicula(4L, "Coco", "Lee Unkrich", "Animacion", 2017));
        pelicula.add(new Pelicula(5L, "Titanic", "James Cameron", "Romance", 1997));
    }

    @GetMapping
    public List<Pelicula> obtenerTodos() {
        return pelicula;
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {
        return pelicula.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula nuevo) {
        pelicula.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Pelicula actualizar(@PathVariable Long id, @RequestBody Pelicula actualizado) {
        for (int i = 0; i < pelicula.size(); i++) {
            if (pelicula.get(i).getId().equals(id)) {
                actualizado.setId(id);
                pelicula.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Pelicula actualizarParcial(@PathVariable Long id, @RequestBody Pelicula cambios) {
    Pelicula existente = obtenerPorId(id);
    if (existente != null) {
        if (cambios.getTitulo() != null) existente.setTitulo(cambios.getTitulo());
        if (cambios.getDirector() != null) existente.setDirector(cambios.getDirector());
        if (cambios.getGenero() != null) existente.setGenero(cambios.getGenero());
        if (cambios.getAñopub() != null) existente.setAñopub(cambios.getAñopub());
    }
    return existente;
}

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        pelicula.removeIf(e -> e.getId().equals(id));
        return "El libro ha sido eliminado correctamente";
    }
    
}
