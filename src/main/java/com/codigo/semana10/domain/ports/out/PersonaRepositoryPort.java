package com.codigo.semana10.domain.ports.out;

import com.codigo.semana10.domain.model.Persona;

import java.util.Optional;

public interface PersonaRepositoryPort {
    Persona save(Persona persona);
    Optional<Persona>findById(Long id);


    Optional<Persona> update(Long id, Persona persona);

    boolean deleteById(Long id);


}