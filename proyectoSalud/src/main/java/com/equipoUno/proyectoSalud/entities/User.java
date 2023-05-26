package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    @JsonIgnore
    protected String id;

    @Column(name = "name")
    protected String name;

    @Column(name = "email")
    protected String email;

    @Column(name = "password")
    @JsonIgnore
    protected String password;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    protected Rol rol;

    @OneToOne
    protected Image image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Professional> professionals;

    @Column(name="created_at")
    protected LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));

}
