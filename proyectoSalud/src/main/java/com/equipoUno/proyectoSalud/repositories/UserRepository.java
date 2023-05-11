package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
