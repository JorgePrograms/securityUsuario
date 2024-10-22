package com.codigo.semana10.infraestructure.controller;

import com.codigo.semana10.application.service.PersonaService;
import com.codigo.semana10.domain.model.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("api/personas")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
        Persona createPersona=personaService.crearPersona(persona);
        return new ResponseEntity<>(createPersona, HttpStatus.CREATED);
    }
    @GetMapping("/{personaId}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long personaId){
        return personaService.getPersona(personaId)
                .map(persona -> new ResponseEntity<>(persona,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{personaId}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long personaId, @RequestBody Persona persona) {
        return personaService.updatePersona(personaId, persona)
                .map(p -> new ResponseEntity<>(p,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @DeleteMapping("/{personaId}")
    public ResponseEntity<Void> deletePersonaById(@PathVariable Long personaId){
        if (personaService.deletePersona(personaId)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}