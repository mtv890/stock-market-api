package com.shilton.stockmarketapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
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

        return new OpenAPI().info(info);
    }


    @Controller
    public static class SwaggerRedirect {
        @RequestMapping(value = "/")
        public String redirectToSwagger() {
            return "redirect:swagger-ui/index.html";
        }
    }
}
