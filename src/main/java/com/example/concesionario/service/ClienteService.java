package com.example.concesionario.service;

import com.example.concesionario.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente save(Cliente empleado);
    List<Cliente> getAllClientes();
    Cliente findClienteById(long id);

    Cliente findClienteByDni(String dni);

    Cliente updateCliente(Cliente cliente);

    void deleteCliente(long id);

    List<Cliente> findByMarca(String ford);
}
