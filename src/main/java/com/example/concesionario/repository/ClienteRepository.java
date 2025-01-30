package com.example.concesionario.repository;

import com.example.concesionario.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByDni(String dni);
    Optional<Cliente> findByDni(String dni);
    List<Cliente> findByNombre(String nombre);
    @Query("SELECT c FROM Cliente c JOIN c.coches coche WHERE coche.marca = :marca")
    List<Cliente> findClientesByCocheMarca(@Param("marca") String marca);
}
