package com.codigo.semana10.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private Long id;
    private String nombreUsuario;
    private String contrasenia;
    private String correoElectronico;
    private String role;
    private Persona persona;

    public Usuario(Long id, String nombreUsuario, String contrasenia, String correoElectronico,String role, Persona persona) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.role=role;
        this.persona = persona;
    }



}
