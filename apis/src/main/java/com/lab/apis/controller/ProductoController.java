package com.lab.apis.controller;

import com.lab.apis.model.Producto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1L, "Laptop", 1200.00, "Tecnología"));
        productos.add(new Producto(2L, "Mouse", 25.50, "Tecnología"));
        productos.add(new Producto(3L, "Teclado", 45.00, "Tecnología"));
        productos.add(new Producto(4L, "Escritorio", 1000.00, "Muebles"));
        productos.add(new Producto(5L, "Monitor", 300.00, "Tecnología"));
    }

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productos;
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Producto crear(@RequestBody Producto nuevo) {
        productos.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto actualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                actualizado.setId(id);
                productos.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Producto actualizarParcial(@PathVariable Long id, @RequestBody Producto cambios) {
        Producto existente = obtenerPorId(id);
        if (existente != null) {
            if (cambios.getNombre() != null) existente.setNombre(cambios.getNombre());
            if (cambios.getPrecio() != null) existente.setPrecio(cambios.getPrecio());
            if (cambios.getCategoria() != null) existente.setCategoria(cambios.getCategoria());
        }
        return existente;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        productos.removeIf(p -> p.getId().equals(id));
        return "Producto eliminado correctamente";
    }
}