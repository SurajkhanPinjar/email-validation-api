package com.emailvalidator.email_validation_api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
public class ApiKeyFilter implements jakarta.servlet.Filter {

    @Value("${security.api.key:}")
    private String internalApiKey;

    @Value("${security.api.enabled:true}")
    private boolean securityEnabled;

    private static final String HEADER_CLIENT = "X-API-KEY";
    private static final String HEADER_RAPID = "X-RapidAPI-Key";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 🚨 1️⃣ Security OFF → allow all
        if (!securityEnabled) {
            chain.doFilter(request, response);
            return;
        }

        // 🚨 2️⃣ Allow Swagger, API Docs & Health
        String path = req.getRequestURI();
        if (path.equals("/ping") ||
                path.contains("swagger") ||
                path.contains("api-docs") ||
                path.contains("v3/api-docs")) {

            chain.doFilter(request, response);
            return;
        }

        // 👀 3️⃣ Log incoming headers (masked)
        log.debug("---- Incoming Request Headers ----");
        Collections.list(req.getHeaderNames())
                .forEach(name -> log.debug("HDR {} = {}", name, req.getHeader(name)));

        String clientKey = req.getHeader(HEADER_CLIENT);
        String rapidKey = req.getHeader(HEADER_RAPID);

        // 📌 Pick the final key (client > rapid)
        String finalKey = clientKey != null ? clientKey : rapidKey;

        log.debug("Client Key: {}", mask(clientKey));
        log.debug("Rapid Key: {}", mask(rapidKey));
        log.debug("Final Key Used: {}", mask(finalKey));

        // 🚨 4️⃣ Missing key → block
        if (finalKey == null || finalKey.isBlank()) {
            unauthorized(res, "Missing API Key");
            return;
        }

        // 🚨 5️⃣ Allow internal (Railway) API key
        if (finalKey.equals(internalApiKey)) {
            chain.doFilter(request, response);
            return;
        }

        // 🚀 6️⃣ Allow RapidAPI marketplace user keys
        if (finalKey.matches("^[A-Za-z0-9]{20,60}$")) {
            chain.doFilter(request, response);
            return;
        }

        // ❌ 7️⃣ Otherwise invalid key
        unauthorized(res, "Invalid API Key");
    }

    // Helper: Send Unauthorized response
    private void unauthorized(HttpServletResponse res, String message) throws IOException {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setContentType("application/json");
        res.getWriter().write("{\"success\":false,\"message\":\"" + message + "\"}");
    }

    // Helper: Mask keys in logs
    private String mask(String key) {
        if (key == null || key.length() < 6) return "******";
        return key.substring(0, 3) + "****" + key.substring(key.length() - 3);
    }
}