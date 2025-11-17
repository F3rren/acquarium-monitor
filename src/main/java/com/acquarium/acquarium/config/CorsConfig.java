// CorsConfig.java
package com.acquarium.acquarium.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permetti credenziali
        config.setAllowCredentials(true);
        
        // Permetti origini specifiche (modifica con il tuo URL frontend)
        config.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "http://localhost:5173",  // Vite
            "http://localhost:4200"   // Angular
        ));
        
        // Permetti tutti gli header
        config.addAllowedHeader("*");
        
        // Permetti tutti i metodi HTTP
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}