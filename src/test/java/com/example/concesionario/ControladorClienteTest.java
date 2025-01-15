package com.example.concesionario;

import com.example.concesionario.model.Cliente;
import com.example.concesionario.service.ClienteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ControladorClienteTest {
    @Autowired
    private ClienteService clienteService;

    @Test
    void testRegistrarCliente() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";

        Cliente cliente = new Cliente(0, dni, nombre, apellido, telefono, new ArrayList<>());
        clienteService.saveCliente(cliente);
        assertEquals(1, cliente.getId());
        assertEquals(cliente, clienteService.findClienteById(1));
    }
/*
    @Test
    void testRegistrarClienteConCoches() throws SQLException{
        String dni = "12345678A";
        String nombre = "Ana";
        String apellido = "Rodríguez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        cliente.addCoche(new Coche("1123HAR", "Ferrari", "Focus", new Date()));
        cliente.addCoche(new Coche("999HAR", "Opel", "Astra", new Date()));
        controladorCliente.registrarCliente(cliente);
        assertEquals(1, cliente.getId());
        Cliente clienteEncontrado = controladorCliente.buscarPorId(1);
        System.out.println("Cliente encontrado: " + clienteEncontrado.getCoches());
        assertEquals(cliente, clienteEncontrado);
    }

    @Test
    void testBuscarClienteCorrecto() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        Cliente loggedInCliente = controladorCliente.buscarPorId(1);
        assertEquals(cliente, loggedInCliente);
    }

    @Test
    void buscarClientePorNombre() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        assertEquals(cliente, controladorCliente.buscarPorNombre(nombre).getFirst());
    }

    @Test
    void testBuscarClienteIncorrecto() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controladorCliente.buscarPorId(2);
        });
        assertEquals("No se encontró el cliente con el id:2", exception.getMessage());
    }

    @Test
    void testActualizarCliente() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        Cliente clienteRegistrado = controladorCliente.buscarPorId(cliente.getId());
        String nuevoNombre = "Carlos";
        String nuevoTelefono = "987654321";
        clienteRegistrado.setNombre(nuevoNombre);
        clienteRegistrado.setTelefono(nuevoTelefono);
        clienteRegistrado.addCoche(new Coche("1123HAR", "Ferrari", "Focus", new Date()));
        controladorCliente.actualizarCliente(clienteRegistrado);
        assertEquals(clienteRegistrado, controladorCliente.buscarPorId(cliente.getId()));
    }

    @Test
    void buscarClientesConFordFocus() throws SQLException {
        String dni = "12345678A";
        String nombre = "Ana";
        String apellido = "Rodríguez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        cliente.addCoche(new Coche("1123HAR", "Ford", "Focus", new Date()));
        cliente.addCoche(new Coche("999HAR", "Opel", "Astra", new Date()));
        controladorCliente.registrarCliente(cliente);
        List<Cliente> clientes = controladorCliente.buscarPorModelo("Ford", "Focus");

        // Verificar que la consulta devuelve al cliente correcto
        assertFalse(clientes.isEmpty(), "No se encontraron clientes con un Ford Focus");
        assertEquals(1, clientes.size(), "Debería haber solo un cliente con un Ford Focus");
        assertEquals(cliente.getDni(), clientes.get(0).getDni(), "El cliente encontrado debe ser el correcto");

        // Imprimir los resultados
        for (Cliente c : clientes) {
            System.out.println("Cliente: " + c.getNombre() + " " + c.getApellidos());
            for (Coche coche : c.getCoches()) {
                System.out.println("Coche: " + coche.getMarca() + " " + coche.getModelo());
            }
        }
    }

    @Test
    void borrarCliente() throws SQLException{
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        controladorCliente.deleteCliente(cliente);
    }

*/

}
