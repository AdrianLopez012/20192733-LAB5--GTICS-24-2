package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lugares")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLugares")
    private Integer id;

    @Column(name = "nombre_lugar", length = 45)
    private String nombreLugar;

    @Column(name = "descrip_lugar", length = 45)
    private String descripcionLugar;

    @Column(name = "fecha_lugar")
    private Date fechaLugar;

    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<Viaje> viajes;
}
