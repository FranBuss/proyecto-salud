package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    protected String id;

    @Column(name = "name")
    protected String name;

    @Column(name = "email")
    protected String email;

    @Column(name = "password")
    protected String password;

    @Enumerated(EnumType.STRING)
    protected Rol rol;

    @OneToOne
    @Column(name = "image")
    protected Image image;

    @Column(name="created_at")
    protected LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));


}
