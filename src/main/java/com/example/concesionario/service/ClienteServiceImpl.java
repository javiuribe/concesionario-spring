package com.example.concesionario.service;

import com.example.concesionario.model.Cliente;
import com.example.concesionario.model.Coche;
import com.example.concesionario.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired  // Inyección automática de la dependencia
    private ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cliente con el DNI:" + cliente.getDni());
        }
        cliente.getCoches().forEach(coche -> coche.setPropietario(cliente));
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findClienteById(long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con Id: " + id));
    }

    @Override
    public Cliente findClienteByDni(String dni){
        return clienteRepository.findByDni(dni).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con Dni: " + dni));
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())){
            return clienteRepository.save(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con Id: " + cliente.getId());
        }
    }

    @Override
    public void deleteCliente(long id) {
        clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con Id: " + id));
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> findByMarca(String marca) {
        return clienteRepository.findClientesByCocheMarca(marca);
    }

    @Override
    public List<Cliente> findByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }
}
