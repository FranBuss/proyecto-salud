package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.email LIKE CONCAT(:email, '@%')")
    public User findByEmailLike(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findByEmail(@Param("email")String email);

}
