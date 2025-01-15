package com.example.concesionario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor //Genera constructor con parámetros
@NoArgsConstructor //Genera constructor sin parámetros
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Coche> coches = new ArrayList<>();

}
