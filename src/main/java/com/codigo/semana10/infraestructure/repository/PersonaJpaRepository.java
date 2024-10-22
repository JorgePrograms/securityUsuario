package com.codigo.semana10.infraestructure.repository;
import com.codigo.semana10.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Long> {

}