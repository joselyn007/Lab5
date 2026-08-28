package com.lab.apis.controller;

import com.lab.apis.model.Cursos;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursosController {

    private List<Cursos> curso = new ArrayList<>();

    public CursosController() {
        curso.add(new Cursos(1L, "Carla Diaz", "Curso de matematicas 1", 5, "Presencial"));
        curso.add(new Cursos(2L, "Rosa Cruz", "Curso intensivo de ingles I", 5, "Virtual" ));
        curso.add(new Cursos(3L, "Santiago rojas", "Curso de Estadistica I", 5, "Presencial"));
        curso.add(new Cursos(4L, "Jonathan Orozco", "Curso de programacion 2", 5,"Virtual"));
        curso.add(new Cursos(5L, "Maria Cachin", "Curso intensivo de programacion basica", 5, "Presencial"));
    }

    @GetMapping
    public List<Cursos> obtenerTodos() {
        return curso;
    }

    @GetMapping("/{id}")
    public Cursos obtenerPorId(@PathVariable Long id) {
        return curso.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Cursos crear(@RequestBody Cursos nuevo) {
        curso.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Cursos actualizar(@PathVariable Long id, @RequestBody Cursos actualizado) {
        for (int i = 0; i < curso.size(); i++) {
            if (curso.get(i).getId().equals(id)) {
                actualizado.setId(id);
                curso.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Cursos actualizarParcial(@PathVariable Long id, @RequestBody Cursos cambios) {
        Cursos existente = obtenerPorId(id);
        if (existente != null) {
            if (cambios.getNombre() != null) existente.setNombre(cambios.getNombre());
            if (cambios.getDescripcion() != null) existente.setDescripcion(cambios.getDescripcion());
            if (cambios.getCreditos() != null) existente.setCreditos(cambios.getCreditos());
            if (cambios.getModalidad() != null) existente.setModalidad(cambios.getModalidad());
        
        }
        return existente;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        curso.removeIf(p -> p.getId().equals(id));
        return "el cursos ha sido eliminado correctamente";
    }
}