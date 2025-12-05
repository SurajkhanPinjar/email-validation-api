package com.emailvalidator.email_validation_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Email Validation API",
                version = "1.0",
                description = "Validates Syntax + MX + SMTP + Disposable email."
        ),
        security = @SecurityRequirement(name = "apiKeyAuth") //  Swagger requires API key
)
@SecurityScheme(
        name = "apiKeyAuth",
        type = SecuritySchemeType.APIKEY,         //  API key auth
        in = SecuritySchemeIn.HEADER,             //  Send it in header
        paramName = "x-api-key"                   //  Header name
)
public class SwaggerConfig {
}