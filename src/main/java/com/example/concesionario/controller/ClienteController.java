package com.example.concesionario.controller;

import com.example.concesionario.model.Cliente;
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
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente empleado){
        System.out.println("Recibido: " + empleado);
        return new ResponseEntity<>(clienteService.saveCliente(empleado),
                HttpStatus.CREATED);
    }
    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteService.getAllClientes();
    }

    // http://localhost:8080/api/empleados/1
    @GetMapping("{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("id") long empleadoId){
        return new ResponseEntity<>(clienteService.findClienteById(empleadoId),
                HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> updateEmpleado(@PathVariable("id") long id
            ,@RequestBody Cliente empleado){
        return new ResponseEntity<>(clienteService.updateCliente(empleado, id),
                HttpStatus.OK);
    }
    // delete empleado REST API
    // http://localhost:8080/api/empleado/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable("id") long id){
        clienteService.deleteCliente(id);
        return new ResponseEntity<>("Empleado borrado correctamente!.", HttpStatus.OK);
    }

}
