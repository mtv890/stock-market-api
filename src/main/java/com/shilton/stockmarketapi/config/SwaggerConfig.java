package com.shilton.stockmarketapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        Contact contact = new Contact();
        contact.setName("Mariano Vico");
        contact.setEmail("mariano@vico.com.ar");
        contact.setUrl("https://github.com/mtv890");

        Info info = new Info()
                .title("Super Simple Stock Market API")
                .description("Super Simple Stock Market API")
                .version("1.0")
                .contact(contact);

        final String securitySchemeName = "bearerAuth";

        Components components = new Components()
                .addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );

        return new OpenAPI()
                .info(info)
                .components(components)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }


    @Controller
    public static class SwaggerRedirect {
        @SuppressWarnings("SameReturnValue")
        @RequestMapping(value = "/")
        public String redirectToSwagger() {
            return "redirect:swagger-ui/index.html";
        }
    }
}
