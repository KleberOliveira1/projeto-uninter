package com.raizesdonordeste.projetobackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Projeto Backend da Uninter",
                version = "v1.1",
                contact = @Contact(
                        name = "Kleber Barbosa de Oliveira",
                        email = "nyder967@gmail.com",
                        url = "https://github.com/KleberOliveira1/projeto-uninter"
                )
        )
)
public class OpenApiConfiguration {

}
