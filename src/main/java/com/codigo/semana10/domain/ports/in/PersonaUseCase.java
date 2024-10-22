package com.codigo.semana10.domain.ports.in;

import com.codigo.semana10.domain.model.Persona;
import java.util.Optional;

public interface PersonaUseCase {
    Persona crearPersona (Persona persona);
    Optional<Persona> getPersona(Long id);//solo obtiene el id

    Optional<Persona> updatePersona(Long id, Persona persona);
    boolean deletePersona(Long id);

}