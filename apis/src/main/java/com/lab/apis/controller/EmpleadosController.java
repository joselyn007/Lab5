package com.lab.apis.controller;

import com.lab.apis.model.Empleados;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadosController {

    private List<Empleados> empleados = new ArrayList<>();

    public EmpleadosController() {
        empleados.add(new Empleados(1L, "Alfonso Gonzales", "Gerente", 7800.00 , "Administracion"));
        empleados.add(new Empleados(2L, "Lorena Cortez", "Secretaria", 4200.00, "Administracion" ));
        empleados.add(new Empleados(3L, "Mario Diaz", "Programador", 6890.00, "TI"));
        empleados.add(new Empleados(4L, "Sandra Correa", "Contador", 8000.00, "Finanzas"));
        empleados.add(new Empleados(5L, "Jonathan Ardiano", "Reclutador", 5000.00, "Recursos humanos"));
    }

    @GetMapping
    public List<Empleados> obtenerTodos() {
        return empleados;
    }

    @GetMapping("/{id}")
    public Empleados obtenerPorId(@PathVariable Long id) {
        return empleados.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Empleados crear(@RequestBody Empleados nuevo) {
        empleados.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Empleados actualizar(@PathVariable Long id, @RequestBody Empleados actualizado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId().equals(id)) {
                actualizado.setId(id);
                empleados.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Empleados actualizarParcial(@PathVariable Long id, @RequestBody Empleados cambios) {
        Empleados existente = obtenerPorId(id);
        if (existente != null) {
            if (cambios.getNombre() != null) existente.setNombre(cambios.getNombre());
            if (cambios.getPuesto() != null) existente.setPuesto(cambios.getPuesto());
            if (cambios.getSalario() != null) existente.setSalario(cambios.getSalario());
            if (cambios.getDepartamento() != null) existente.setDepartamento(cambios.getDepartamento());
        }
        return existente;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        empleados.removeIf(e -> e.getId().equals(id));
        return "Estudiante eliminado correctamente";
    }
}