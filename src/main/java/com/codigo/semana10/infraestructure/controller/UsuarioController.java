package com.codigo.semana10.infraestructure.controller;

import com.codigo.semana10.application.service.UsuarioService;
import com.codigo.semana10.domain.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){

        Usuario createUsuario= usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(createUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario>getUsuarioById(@PathVariable Long usuarioId){
        return usuarioService.getUsuario(usuarioId)
                .map(usu-> new ResponseEntity<>(usu,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario>updateUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(usuarioId,usuario)
                .map(u->new ResponseEntity<>(u,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long usuarioId){
        if (usuarioService.deleteUsuario(usuarioId)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    @PostMapping("/login")
    public String loginWithUser(@RequestBody(required = true) Map<String,String> requestMap){
        return usuarioService.loginWithUser(requestMap);
    }

}
