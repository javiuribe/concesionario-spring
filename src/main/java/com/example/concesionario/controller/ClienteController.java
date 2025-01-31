package com.example.concesionario.controller;

import com.example.concesionario.model.Cliente;
import com.example.concesionario.model.Coche;
import com.example.concesionario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired  // Inyección automática de la dependencia
    private ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Cliente> findAll(@RequestParam(value = "marca", required = false) String marca,
                                 @RequestParam(value = "nombre", required = false) String nombre) {
        if (marca != null) {
            return clienteService.findByMarca(marca);
        }
        if (nombre != null) {
            return clienteService.findByNombre(nombre);
        }
        return clienteService.getAllClientes();
    }

    // http://localhost:8080/api/clientes/1
    @GetMapping("{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("id") long clienteId) {
        clienteService.findClienteById(clienteId).getDni();
        return new ResponseEntity<>(clienteService.findClienteById(clienteId),
                HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") long id
            , @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.updateCliente(cliente),
                HttpStatus.OK);
    }

    // http://localhost:8080/api/cliente/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id") long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
