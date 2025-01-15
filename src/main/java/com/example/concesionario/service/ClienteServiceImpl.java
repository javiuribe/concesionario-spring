package com.example.concesionario.service;

import com.example.concesionario.model.Cliente;
import com.example.concesionario.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired  // Inyección automática de la dependencia
    private ClienteRepository clienteRepository;

    @Override
    public Cliente saveCliente(Cliente cliente) {
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cliente con el DNI:" + cliente.getDni());
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findClienteById(long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con Id: " + id));
    }

    @Override
    public Cliente updateCliente(Cliente cliente, long id) {
        Cliente existingEmpleado = clienteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado con Id: " + id));
        clienteRepository.save(cliente);
        return existingEmpleado;
    }

    @Override
    public void deleteCliente(long id) {
        clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado con Id: " + id));
        clienteRepository.deleteById(id);
    }
}
