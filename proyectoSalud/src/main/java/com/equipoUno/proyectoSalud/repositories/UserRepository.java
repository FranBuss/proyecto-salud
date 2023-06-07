package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /*
     * ESTO ESTA MAL IMPLEMENTADO, PERO POR FALTA DE TIEMPO LO DEJÉ ASI, EL PROBLEMA
     * CON ESTO ES QUE NO EVALUA EL DOMINIO Y NO PODRIAN HABER 2 EMAILS CON MISMO
     * NOMBRE PERO DIFERENTE DOMINIO.FS
     */
    @Query("SELECT u FROM User u WHERE u.email LIKE CONCAT(:email, '@%')")
    public User findByEmailLike(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.rol != 'ADMIN'")
    public List<User> getUsers();
}
