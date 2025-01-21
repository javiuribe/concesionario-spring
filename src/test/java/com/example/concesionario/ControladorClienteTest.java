package com.example.concesionario;

import com.example.concesionario.controller.ClienteController;
import com.example.concesionario.model.Cliente;
import com.example.concesionario.model.Coche;
import com.example.concesionario.service.ClienteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ControladorClienteTest {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteController clienteController;

    private Cliente crearCliente() {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";

        return new Cliente(null, dni, nombre, apellido, telefono, new ArrayList<>());
    }

    private Cliente crearClienteCoches() {
        Cliente cliente = crearCliente();
        Coche coche = new Coche(null, "1234Z", "Opel", "Corsa", LocalDate.now(), cliente);
        Coche coche2 = new Coche(null, "9876Z", "Ford", "Focus", LocalDate.now(), cliente);
        List<Coche> coches = new ArrayList<>();
        coches.add(coche);
        coches.add(coche2);
        cliente.setCoches(coches);
        return cliente;
    }

    @Test
    void testRegistrarCliente() {
        Cliente cliente = crearCliente();
        clienteService.save(cliente);
        Cliente clienteBD = clienteService.findClienteById(1);
        clienteBD.setCoches(new ArrayList<>(clienteBD.getCoches()));
        cliente.setCoches(new ArrayList<>(cliente.getCoches()));
        assertEquals(cliente, clienteBD);
    }

    @Test
    void testRegistrarClienteConCoches() {
        Cliente cliente = crearClienteCoches();
        clienteService.save(cliente);
        Cliente clienteBD = clienteService.findClienteById(1);
        clienteBD.setCoches(new ArrayList<>(clienteBD.getCoches()));
        cliente.setCoches(new ArrayList<>(cliente.getCoches()));
        assertEquals(cliente, clienteBD);
    }

    @Test
    void buscarClientePorDni() throws SQLException {
        Cliente cliente = crearCliente();
        clienteService.save(cliente);
        Cliente clienteBD = clienteService.findClienteByDni(cliente.getDni());
        clienteBD.setCoches(new ArrayList<>(clienteBD.getCoches()));
        cliente.setCoches(new ArrayList<>(cliente.getCoches()));
        assertEquals(cliente, clienteBD);
    }

    @Test
    void testBuscarClienteIncorrecto() throws SQLException {
        Cliente cliente = crearCliente();
        clienteService.save(cliente);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            clienteService.findClienteById(2);
        });
        assertEquals("404 NOT_FOUND \"Cliente no encontrado con Id: 2\"", exception.getMessage());
    }

    @Test
    void testActualizarCliente() throws SQLException {
        Cliente cliente = crearCliente();
        clienteService.save(cliente);
        cliente.setNombre("Andres Martinez");
        List<Coche> coches = cliente.getCoches();
        coches.add(new Coche(null, "1123HAR", "Ferrari", "Focus", LocalDate.now(), cliente));
        cliente.setCoches(coches);
        cliente = clienteService.updateCliente(cliente);
        Cliente clienteBD = clienteService.findClienteById(cliente.getId());
        clienteBD.setCoches(new ArrayList<>(clienteBD.getCoches()));
        cliente.setCoches(new ArrayList<>(cliente.getCoches()));
        assertEquals(cliente, clienteBD);
    }

    @Test
    void buscarClientesConFordFocus() throws SQLException {
        Cliente cliente = crearClienteCoches();
        clienteService.save(cliente);
        List<Cliente> clientes = clienteService.findByMarca("Ford");
        System.out.println(clientes);
        // Verificar que la consulta devuelve al cliente correcto
        assertFalse(clientes.isEmpty(), "No se encontraron clientes con un Ford Focus");
        assertEquals(1, clientes.size(), "Debería haber solo un cliente con un Ford Focus");
        assertEquals(cliente.getDni(), clientes.get(0).getDni(), "El cliente encontrado debe ser el correcto");
    }


    @Test
    void borrarCliente() throws SQLException{
        Cliente cliente = crearCliente();
        clienteService.save(cliente);
        assertFalse(clienteController.findAll().isEmpty());
        clienteService.deleteCliente(cliente.getId());
        assertTrue(clienteController.findAll().isEmpty());
    }


}
