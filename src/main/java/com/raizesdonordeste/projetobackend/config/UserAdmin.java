package com.raizesdonordeste.projetobackend.config;

import com.raizesdonordeste.projetobackend.model.Role;
import com.raizesdonordeste.projetobackend.model.Usuario;
import com.raizesdonordeste.projetobackend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAdmin implements CommandLineRunner {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;


    public UserAdmin(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String nome = "usuarioAdmin";
        if (!repository.existsByNome(nome)) {
            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setEmail("usuarioAdmin@gmail.com");
            usuarioAdmin.setNome(nome);
            usuarioAdmin.setPasswordHash(encoder.encode("1234"));
            usuarioAdmin.setRole(Role.ADMIN);

            repository.save(usuarioAdmin);

            System.out.println("Usuario admin persistido");
        }
    }
}