package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "dni", nullable = false, length = 45)
    private String dni;

    @Column(name = "celular", nullable = false, length = 45)
    private String celular;

    @Column(name = "tipo_persona", nullable = false, length = 45)
    private String tipoPersona;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Mascota> mascotas;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Viaje> viajes;
}
