//package com.example.main.config;
//
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springdoc.core.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class SwaggerConfiguration {
//    @Bean
//    public OpenAPI customOpenAPI() {
//        Contact contact = new Contact();
//        contact.setName("Muhammad Ilham Hidayat");
//        contact.setEmail("muhammadilhamhidayat3@gmail.com");
//        contact.setUrl("https://github.com/ilhamdivel");
//
//        final String securitySchemeName = "bearerAuth";
//
//        return new OpenAPI()
//                .info(new Info().title("Online Food API")
//                        .contact(contact)
//                        .description("Spring Boot REST API For Order Food Or Drink")
//                        .version("1.0"))
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(
//                        new Components()
//                                .addSecuritySchemes(securitySchemeName,
//                                        new SecurityScheme()
//                                                .name(securitySchemeName)
//                                                .type(SecurityScheme.Type.HTTP)
//                                                .scheme("bearer")
//                                                .bearerFormat("JWT")
//                                )
//                );
//    }
//}
