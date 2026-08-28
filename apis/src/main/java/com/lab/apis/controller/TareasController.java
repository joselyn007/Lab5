package com.lab.apis.controller;

import com.lab.apis.model.Tareas;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareasController {

    private List<Tareas> tareas = new ArrayList<>();

    public TareasController() {
        tareas.add(new Tareas(1L, "Leer libro", "Avanzar 20 páginas", "Media", "Sí"));
        tareas.add(new Tareas(2L, "Desarrollo humano", "Hacer el resumen del tema", "Alta", "No"));
        tareas.add(new Tareas(3L, "Contabilidad", "Repasar el ejercicio", "Alta", "No"));
        tareas.add(new Tareas(4L, "Estudiar para examen", "Repasar los temas principales", "Alta", "No"));
        tareas.add(new Tareas(5L, "Entregar proyecto", "Subir el documento a la plataforma", "Media", "Sí"));
    }

    @GetMapping
    public List<Tareas> obtenerTodas() {
        return tareas;
    }

    @GetMapping("/{id}")
    public Tareas obtenerPorId(@PathVariable Long id) {
        return tareas.stream()
                .filter(t -> t.getId() != null && t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Tareas crear(@RequestBody Tareas nueva) {
        tareas.add(nueva);
        return nueva;
    }

    @PutMapping("/{id}")
    public Tareas actualizar(@PathVariable Long id, @RequestBody Tareas actualizada) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() != null && tareas.get(i).getId().equals(id)) {
                actualizada.setId(id);
                tareas.set(i, actualizada);
                return actualizada;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Tareas actualizarParcial(@PathVariable Long id, @RequestBody Tareas cambios) {
        Tareas existente = obtenerPorId(id);
        if (existente != null) {
            if (cambios.getTitulo() != null) existente.setTitulo(cambios.getTitulo());
            if (cambios.getDescripcion() != null) existente.setDescripcion(cambios.getDescripcion());
            if (cambios.getPrioridad() != null) existente.setPrioridad(cambios.getPrioridad());
            if (cambios.getCompletada() != null) existente.setCompletada(cambios.getCompletada());
        }
        return existente;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        tareas.removeIf(t -> t.getId() != null && t.getId().equals(id));
        return "La tarea ha sido eliminada correctamente";
    }
}