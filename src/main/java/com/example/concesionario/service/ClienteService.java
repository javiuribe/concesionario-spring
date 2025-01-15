package com.example.concesionario.service;

import com.example.concesionario.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente saveCliente(Cliente empleado);
    List<Cliente> getAllClientes();
    Cliente findClienteById(long id);
    Cliente updateCliente(Cliente empleado, long id);
    void deleteCliente(long id);
}
