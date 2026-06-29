package com.raizesdonordeste.projetobackend.service;

import com.raizesdonordeste.projetobackend.dto.UsuarioRequest;
import com.raizesdonordeste.projetobackend.model.Role;
import com.raizesdonordeste.projetobackend.model.Usuario;
import com.raizesdonordeste.projetobackend.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    public Usuario registrar (UsuarioRequest request){

        Role role = Role.valueOf(request.getRole());

        Usuario usuarioSalvo = new Usuario(
                request.getNome(),
                request.getEmail(),
                encoder.encode(request.getSenha()),
                role
        );

        return usuarioRepository.save(usuarioSalvo);
    }

    public Usuario consultarUsuario(UUID id){
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

    }

    public void deletarUsuario(UUID id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));
        usuarioRepository.deleteById(usuario.getId());
    }
}