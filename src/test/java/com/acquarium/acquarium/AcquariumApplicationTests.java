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
import org.springframework.transaction.annotation.Transactional;

import com.acquarium.acquarium.models.Aquarium;
import com.acquarium.acquarium.services.AquariumService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AquariumServiceTest {

	@Autowired
    private TestRestTemplate restTemplate;

	@Autowired
	private AquariumService aquariumService;


	@Test
	@Transactional
	void testAquariumCreation(){
		Aquarium aquarium = new Aquarium("My aquarium", 150);
		Aquarium saved = aquariumService.createAquarium(aquarium);

		assertNotNull(saved.getId());
		assertEquals("My aquarium", saved.getName());
		assertEquals(150, saved.getVolume());
	}

	@Test
	@Transactional
    void testCreateAndGetAquarium() {
        // Crea un acquario
        Aquarium aquarium = new Aquarium("Test Aquarium", 100);
        
        ResponseEntity<Map> createResponse = restTemplate.postForEntity(
            "/api/aquariums", 
            aquarium, 
            Map.class
        );
        
        assertEquals(createResponse.getStatusCode(), HttpStatus.CREATED);
        
        // Recupera l'acquario creato
        ResponseEntity<Map> getResponse = restTemplate.getForEntity(
            "/api/aquariums/1", 
            Map.class
        );
        
        assertEquals(getResponse.getStatusCode(), HttpStatus.OK);
    }

}
