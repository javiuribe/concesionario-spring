package com.example.concesionario.controller;

import com.example.concesionario.model.Cliente;
import com.example.concesionario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        //Para enviar datos desde el Controller a la vista se usa model que almacena pares clave-valor
        model.addAttribute("clientes", clienteService.findAllClientes());
        System.out.println("Entra en clientes");
        return "clientes"; // El fichero clientes.html debe existir en resources/templates
    }

    @GetMapping("/{id}")
    public String verCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.findClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente-detalle"; // El fichero cliente-detalle.html debe existir en resources/templates
    }
}
