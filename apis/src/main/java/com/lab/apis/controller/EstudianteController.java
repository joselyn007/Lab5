package com.lab.apis.controller;

import com.lab.apis.model.Estudiante;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private List<Estudiante> estudiantes = new ArrayList<>();

    public EstudianteController() {
        estudiantes.add(new Estudiante(1L, "Carlos", "Pérez", "Ingeniería en Sistemas", 20));
        estudiantes.add(new Estudiante(2L, "María", "López", "Derecho", 22));
        estudiantes.add(new Estudiante(3L, "Juan", "Gómez", "Administración", 21));
        estudiantes.add(new Estudiante(4L, "Ana", "Martínez", "Medicina", 23));
        estudiantes.add(new Estudiante(5L, "Luis", "Hernández", "Arquitectura", 19));
    }

    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudiantes;
    }

    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable Long id) {
        return estudiantes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante nuevo) {
        estudiantes.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante actualizado) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getId().equals(id)) {
                actualizado.setId(id);
                estudiantes.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Estudiante actualizarParcial(@PathVariable Long id, @RequestBody Estudiante cambios) {
        Estudiante existente = obtenerPorId(id);
        if (existente != null) {
            if (cambios.getNombre() != null) existente.setNombre(cambios.getNombre());
            if (cambios.getApellido() != null) existente.setApellido(cambios.getApellido());
            if (cambios.getCarrera() != null) existente.setCarrera(cambios.getCarrera());
            if (cambios.getEdad() != null) existente.setEdad(cambios.getEdad());
        }
        return existente;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        estudiantes.removeIf(e -> e.getId().equals(id));
        return "Estudiante eliminado correctamente";
    }
}