package com.codigo.semana10.infraestructure.repository;

import com.codigo.semana10.domain.model.Persona;
import com.codigo.semana10.domain.ports.out.PersonaRepositoryPort;
import com.codigo.semana10.infraestructure.entity.PersonaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class PersonaJpaRepositoryAdapter implements PersonaRepositoryPort {
    private final PersonaJpaRepository personaJpaRepository;

    public PersonaJpaRepositoryAdapter(PersonaJpaRepository personaJpaRepository) {
        this.personaJpaRepository = personaJpaRepository;
    }
    @Override
    public Persona save(Persona persona) {
        PersonaEntity personaEntity = PersonaEntity.fromDomainModel(persona);
        PersonaEntity savePersonaEntity = personaJpaRepository.save(personaEntity);
        return savePersonaEntity.toDomainModel();
    }
    @Override
    public Optional<Persona> findById(Long id) {
        return personaJpaRepository.findById(id).map(PersonaEntity::toDomainModel);
    }
    @Override
    public Optional<Persona> update(Long id, Persona persona) {
        if (id != null && personaJpaRepository.existsById(id)){
            PersonaEntity personaEntity = PersonaEntity.fromDomainModel(persona);
            personaEntity.setId(id);
            PersonaEntity updatepersonaEntity = personaJpaRepository.save(personaEntity);
            return Optional.of(updatepersonaEntity.toDomainModel());
        }
        return Optional.empty();
    }
    @Override
    public boolean deleteById(Long id){
        if (personaJpaRepository.existsById(id)) {
            personaJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


