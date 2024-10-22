package com.codigo.semana10.infraestructure.repository;

import com.codigo.semana10.domain.model.Usuario;
import com.codigo.semana10.domain.ports.out.UsuarioRepositoryPort;
import com.codigo.semana10.infraestructure.entity.UsuarioEntity;
import com.codigo.semana10.infraestructure.security.CustomerDetailService;
import com.codigo.semana10.infraestructure.security.Jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.Optional;

@Component
public class UsuarioJpaRepositoryAdapater implements UsuarioRepositoryPort {
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomerDetailService customerDetailService;

    public UsuarioJpaRepositoryAdapater(UsuarioJpaRepository usuarioJpaRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomerDetailService customerDetailService) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customerDetailService = customerDetailService;
    }

    @Override
    public Usuario save(Usuario usuario){
        UsuarioEntity usuarioEntity=UsuarioEntity.fromDomainModel(usuario);
        UsuarioEntity saveUsuarioEntity=usuarioJpaRepository.save(usuarioEntity);
        return saveUsuarioEntity.toDomainModel();
    }

    @Override
    public String loginWhithUser(Map<String, String> requestMap) {

        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestMap.get("nombreUsuario"),
                            requestMap.get("contrasenia")));
            if (authentication.isAuthenticated()){
                String token = jwtUtil.generateToken(
                        customerDetailService.getUserDetail().getNombreUsuario(),
                        customerDetailService.getUserDetail().getRole());

                // Formatear correctamente el JSON
                return "{\"token\": \"" + token + "\"}";

            }

        }catch (Exception e){
            e.printStackTrace();
        }
   return null;
    }

    @Override
    public Optional<Usuario> findById(Long id){
        return usuarioJpaRepository.findById(id)
                .map(UsuarioEntity::toDomainModel);
    }
    @Override
    public Optional<Usuario> update(Long id, Usuario usuario){
        if (usuarioJpaRepository.existsById(id)) {
            UsuarioEntity usuarioEntity=UsuarioEntity.fromDomainModel(usuario);
            UsuarioEntity updateUsuarioEntity=usuarioJpaRepository.save(usuarioEntity);
            return Optional.of(updateUsuarioEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id){
        if(usuarioJpaRepository.existsById(id)){
            usuarioJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
