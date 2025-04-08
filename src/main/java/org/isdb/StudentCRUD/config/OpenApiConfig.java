package org.isdb.StudentCRUD.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${springdoc.swagger-ui.path:/swagger-ui.html}")
    private String swaggerPath;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName + " API Documentation")
                        .description("API documentation for the " + applicationName + " application.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("API Support Team")
                                .email("nafiuzzaman.parves@gmail.com")
                                .url("https://example.com/support"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .termsOfService("https://example.com/terms"))
                .externalDocs(new ExternalDocumentation()
                        .description("Additional Documentation")
                        .url("https://example.com/docs"))
                .servers(Arrays.asList(
                        new Server().url("http://localhost:8081").description("Local Development Server"),
                        new Server().url("https://dev.example.com").description("Development Server"),
                        new Server().url("https://api.example.com").description("Production Server")
                ));
    }
}
