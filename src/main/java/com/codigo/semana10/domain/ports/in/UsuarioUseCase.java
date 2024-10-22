package com.codigo.semana10.domain.ports.in;

import com.codigo.semana10.domain.model.Usuario;

import java.util.Map;
import java.util.Optional;

public interface UsuarioUseCase {

    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> getUsuario(Long id);
    Optional<Usuario> updateUsuario(Long id,Usuario usuario);
    boolean deleteUsuario(Long id);

    String loginWithUser(Map<String,String> requestMap);

}
