package com.lab.apis.controller;

import com.lab.apis.model.Pedidos;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private List<Pedidos> pedidos = new ArrayList<>();

    public PedidosController() {
        pedidos.add(new Pedidos(1L, "Carlos Mendoza", "Laptop HP", 1, 6500.0, "PENDIENTE"));
        pedidos.add(new Pedidos(2L, "Ana López", "Mouse Inalámbrico", 2, 350.0, "ENVIADO"));
        pedidos.add(new Pedidos(3L, "Luis García", "Teclado Mecánico", 1, 800.0, "ENTREGADO"));
        pedidos.add(new Pedidos(4L, "María Rodríguez", "Monitor 24 pulgadas", 1, 1800.0, "PENDIENTE"));
        pedidos.add(new Pedidos(5L, "Jorge Martínez", "Audífonos Bluetooth", 1, 450.0, "CANCELADO"));
    }

    @GetMapping
    public List<Pedidos> obtenerTodos() {
        return pedidos;
    }

    @GetMapping("/{id}")
    public Pedidos obtenerPorId(@PathVariable Long id) {
        return pedidos.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Pedidos crear(@RequestBody Pedidos nuevo) {
        pedidos.add(nuevo);
        return nuevo;
    }

    @PutMapping("/{id}")
    public Pedidos actualizar(@PathVariable Long id, @RequestBody Pedidos actualizado) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() != null && pedidos.get(i).getId().equals(id)) {
                actualizado.setId(id);
                pedidos.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Pedidos actualizarParcial(@PathVariable Long id, @RequestBody Pedidos cambios) {
        Pedidos existente = obtenerPorId(id);
        if (existente != null) {
            if (cambios.getCliente() != null) existente.setCliente(cambios.getCliente());
            if (cambios.getProducto() != null) existente.setProducto(cambios.getProducto());
            if (cambios.getCantidad() != null) existente.setCantidad(cambios.getCantidad());
            if (cambios.getTotal() != null) existente.setTotal(cambios.getTotal());
            if (cambios.getEstado() != null) existente.setEstado(cambios.getEstado());
        }
        return existente;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        pedidos.removeIf(p -> p.getId() != null && p.getId().equals(id));
        return "El pedido ha sido eliminado correctamente";
    }
}