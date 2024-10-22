package com.codigo.semana10.infraestructure.repository;

import com.codigo.semana10.infraestructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByNombreUsuario(String nombreUsuario);
}
