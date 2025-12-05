package com.emailvalidator.email_validation_api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DisposableEmailUtil {

    private static final Set<String> DISPOSABLE_DOMAINS = new HashSet<>();

    static {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        DisposableEmailUtil.class.getResourceAsStream("/disposable_domains.txt")
                ))) {

            String line;
            while ((line = reader.readLine()) != null) {
                DISPOSABLE_DOMAINS.add(line.trim().toLowerCase());
            }

        } catch (Exception e) {
            System.out.println("Failed to load disposable email list: " + e.getMessage());
        }
    }

    public static boolean isDisposable(String email) {
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        return DISPOSABLE_DOMAINS.contains(domain);
    }
}