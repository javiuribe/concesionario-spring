package com.example.concesionario.service;

import com.example.concesionario.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente save(Cliente cliente);
    List<Cliente> findAllClientes();
    Cliente findClienteById(long id);

    Cliente findClienteByDni(String dni);

    Cliente updateCliente(Cliente cliente);

    void deleteCliente(long id);

    List<Cliente> findByMarca(String ford);

    List<Cliente> findByNombre(String nombre);
}
