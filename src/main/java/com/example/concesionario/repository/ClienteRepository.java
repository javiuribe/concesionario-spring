package com.example.concesionario.repository;

import com.example.concesionario.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByDni(String dni);
}
