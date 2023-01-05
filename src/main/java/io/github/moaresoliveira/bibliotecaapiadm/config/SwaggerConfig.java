package io.github.moaresoliveira.bibliotecaapiadm.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI BibliotecaAPI() {
        return new OpenAPI()
                .info(new Info().title("Biblioteca Admin API")
                        .description("API de Gerenciamento de Clientes e Funcionários da Biblioteca da Dona Gertrudes")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("Moares Oliveira")
                                .email("moaresoliveira@gmail.com")
                                .url("https://github.com/MoaresOliveira")));
    }

}
