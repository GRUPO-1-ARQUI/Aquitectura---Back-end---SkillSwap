package com.upc.innovify.repository;

import com.upc.innovify.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreoInstitucional(String correo);

    List<Usuario> findByRol(String rol);

    // US49 - Listar estudiantes con paginación
    Page<Usuario> findByRol(String rol, Pageable pageable);

    @Query("""
        SELECT u
        FROM Usuario u
        WHERE u.rol = 'estudiante'
        AND (
            LOWER(u.nombreCompleto) LIKE LOWER(CONCAT('%', :texto, '%'))
            OR LOWER(u.codigoEstudiante) LIKE LOWER(CONCAT('%', :texto, '%'))
        )
    """)
    List<Usuario> buscarEstudiantes(@Param("texto") String texto);

    List<Usuario> findByIdInstitucion(Integer idInstitucion);

    List<Usuario> findByIdInstitucionAndRol(Integer idInstitucion, String rol);

    List<Usuario> findByRolAndDisponible(String rol, Boolean disponible);

}