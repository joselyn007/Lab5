package com.lab.apis.controller;

import com.lab.apis.model.Clientes;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    private List<Clientes> clientes = new ArrayList<>();

    public ClientesController() {
        clientes.add(new Clientes(1L, "Carlos", "Mendoza", "carlos.mendoza@email.com", "5551-2345"));
        clientes.add(new Clientes(2L, "Ana", "López", "ana.lopez@email.com", "5552-3456"));
        clientes.add(new Clientes(3L, "Luis", "García", "luis.garcia@email.com", "5553-4567"));
        clientes.add(new Clientes(4L, "María", "Rodríguez", "maria.rodriguez@email.com", "5554-5678"));
        clientes.add(new Clientes(5L, "Jorge", "Martínez", "jorge.martinez@email.com", "5555-6789"));
    }

    @GetMapping
    public List<Clientes> obtenerTodos() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Clientes obtenerPorId(@PathVariable Long id) {
        return clientes.stream()
                .filter(c -> c.getId() != null && c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Clientes crear(@RequestBody Clientes nuevo) {
        clientes.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Clientes actualizar(@PathVariable Long id, @RequestBody Clientes actualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() != null && clientes.get(i).getId().equals(id)) {
                actualizado.setId(id);
                clientes.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Clientes actualizarParcial(@PathVariable Long id, @RequestBody Clientes cambios) {
        Clientes existente = obtenerPorId(id);
        if (existente != null) {
            if (cambios.getNombre() != null) existente.setNombre(cambios.getNombre());
            if (cambios.getApellido() != null) existente.setApellido(cambios.getApellido());
            if (cambios.getCorreo() != null) existente.setCorreo(cambios.getCorreo());
            if (cambios.getTelefono() != null) existente.setTelefono(cambios.getTelefono());
        }
        return existente;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        clientes.removeIf(c -> c.getId() != null && c.getId().equals(id));
        return "El cliente ha sido eliminado correctamente";
    }
}