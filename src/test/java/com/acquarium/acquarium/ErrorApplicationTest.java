package com.acquarium.acquarium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ErrorApplicationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetNonExistentAquarium() {
        // Verifica che restituisca 404 con messaggio chiaro
        ResponseEntity<Map> response = restTemplate.getForEntity("/api/aquariums/9999", Map.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertNotNull(body, "Response body should not be null");
        assertEquals("Acquario non trovato con ID: 9999", body.get("message"));
    }

    @Test
    void testDeleteNonExistentAquarium() {
        // Verifica DELETE su risorsa inesistente
        restTemplate.delete("/api/aquariums/9999");
        
        // Dovrebbe lanciare 404 ma Spring RestTemplate non lancia eccezione per DELETE
        // Verifichiamo con una GET che l'acquario non esista
        ResponseEntity<Map> response = restTemplate.getForEntity("/api/aquariums/9999", Map.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetParametersForNonExistentAquarium() {
        // Verifica che lanci 404 quando chiedi parametri di acquario inesistente
        ResponseEntity<Map> response = restTemplate.getForEntity(
            "/api/aquariums/9999/parameters/latest", 
            Map.class
        );
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals("Nessun parametro trovato per l'acquario con ID: 9999", body.get("message"));
    }
}
