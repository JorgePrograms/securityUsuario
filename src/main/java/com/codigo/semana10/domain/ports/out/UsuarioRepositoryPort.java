package com.codigo.semana10.domain.ports.out;

import com.codigo.semana10.domain.model.Usuario;

import java.util.Map;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario save (Usuario usuario);
    Optional<Usuario> findById(Long id);

    Optional<Usuario> update (Long id, Usuario usuario);
    boolean deleteById(Long id);

    String loginWhithUser(Map<String,String> requestMap);

}
