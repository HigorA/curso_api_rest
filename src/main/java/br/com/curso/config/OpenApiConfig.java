package br.com.curso.config;

import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("RESTFUL API Java 18 - Spring boot 3")
                .version("v1")
                .description("API desenvolvida no curso de APIs Restful")
                .termsOfService("")
                .license(new License().name("Apache 2.0").url(""))
        );
    }
}
