package com.emailvalidator.email_validation_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Email Validation API",
                version = "1.0",
                description = "API to validate email syntax, MX, SMTP, disposable check.",
                contact = @Contact(
                        name = "Your Name",
                        email = "your-email@gmail.com"
                )
        )
)
public class SwaggerConfig {

}