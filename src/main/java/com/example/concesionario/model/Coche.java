package com.example.concesionario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor //Genera constructor con parámetros
@NoArgsConstructor //Genera constructor sin parámetros
@EqualsAndHashCode
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String marca;
    private String modelo;
    private LocalDate fecha;
    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Cliente propietario;

}
