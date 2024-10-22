package com.codigo.semana10.infraestructure.entity;

import com.codigo.semana10.domain.model.Persona;

//import javax.persistence.*;
//import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Setter
@Getter
@Table(name="personas")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;

    public PersonaEntity() {
    }

    public PersonaEntity(Long id, String nombre, String apellido, Date fechaNacimiento, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public static PersonaEntity fromDomainModel(Persona persona){
        if(persona==null){
            return null;
        }
        return new PersonaEntity(persona.getId(),persona.getNombre(),persona.getApellido(),persona.getFechaNacimiento(),persona.getGenero());
    }

    public Persona toDomainModel(){
        return new Persona(id, nombre,apellido,fechaNacimiento,genero);
    }


}