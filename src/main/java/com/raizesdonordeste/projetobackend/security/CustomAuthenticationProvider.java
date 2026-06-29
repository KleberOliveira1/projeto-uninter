package com.raizesdonordeste.projetobackend.security;

import com.raizesdonordeste.projetobackend.model.Usuario;
import com.raizesdonordeste.projetobackend.repository.UsuarioRepository;
import com.raizesdonordeste.projetobackend.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder encoder;
    private final UsuarioRepository repository;

    public CustomAuthenticationProvider(PasswordEncoder encoder, UsuarioRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginEmail = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Usuario usuario = repository.findByEmail(loginEmail)
                .orElseThrow(()->new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(senha, usuario.getPasswordHash())){
            throw new RuntimeException("senha inválida");
        }

        return new CustomAuthentication(usuario);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(authentication);
    }
}