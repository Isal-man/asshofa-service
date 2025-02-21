package com.asshofa.management.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private static final String BEARER_AUTH = "bearerAuth";

    @Value("${url_prod}")
    private String url;

    @Bean
    public OpenAPI springAppOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Asshofa Management Service")
                        .description("RESTful API For Asshofa Management")
                        .version("v0.0.1")
                        .license(new License().name("For Internal Use Only").url("https://asshofa.sch"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot Documentation")
                        .url("https://bit.ly/4cFhdZi")
                )
                .servers(Collections.singletonList(
                        new Server().url(url)
                                .description("Production server")
                ))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH))
                .components(new Components().addSecuritySchemes(BEARER_AUTH,
                        new SecurityScheme()
                                .name(BEARER_AUTH)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
