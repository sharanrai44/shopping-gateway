package com.onlineshopping.api_gateway.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
                .group("Product Service")
                .pathsToMatch("/products/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authApi() {
            return GroupedOpenApi.builder()
                    .group("Auth Service")
                    .addOpenApiCustomizer(openApi -> openApi.setServers(List.of(
                            new Server().url("http://127.0.0.1:8765")
                                    .description("Gateway URL"))))
                    .pathsToMatch("/auth/**")
                    .build();
        }


    // Add more services as needed
}
