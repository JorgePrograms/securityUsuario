package com.codigo.semana10.infraestructure.entity;


import com.codigo.semana10.domain.model.Persona;
import com.codigo.semana10.domain.model.Usuario;
import lombok.*;

import jakarta.persistence.*;


@Entity
@Setter
@Getter
@Table(name="usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="nombre_usuario", nullable = false)

    private String nombreUsuario;
    private String contrasenia;
    @Column (name = "correo_electronico")
    private String correoElectronico;
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private PersonaEntity personaEntity;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String nombreUsuario, String contrasenia, String correoElectronico,String role, PersonaEntity personaEntity) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.role=role;
        this.personaEntity = personaEntity;
    }

    public static UsuarioEntity fromDomainModel(Usuario usuario){
      /*  if(usuario==null){
            return null;
        }*/
        return new UsuarioEntity(usuario.getId(),usuario.getNombreUsuario(),usuario.getContrasenia(),usuario.getCorreoElectronico(),usuario.getRole(),PersonaEntity.fromDomainModel(usuario.getPersona()));
    }

    public Usuario toDomainModel(){
       Persona persona = (personaEntity != null) ? personaEntity.toDomainModel() : null;

        return new Usuario(id,nombreUsuario,contrasenia,correoElectronico,role,persona);
    }




}
