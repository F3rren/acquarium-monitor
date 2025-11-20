package com.acquarium.acquarium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.acquarium.acquarium.models.Aquarium;
import com.acquarium.acquarium.services.AquariumService;

@SpringBootTest
@ActiveProfiles("test")
class AquariumServiceTest {

	@Autowired
	private AquariumService aquariumService;

	@Test
	void testAquariumCreation(){
		Aquarium aquarium = new Aquarium("My aquarium", 150);
		Aquarium saved = aquariumService.createAquarium(aquarium);

		assertNotNull(saved.getId());
		assertEquals("My aquarium", saved.getName());
		assertEquals(150, saved.getVolume());

	}


	@Test
	void contextLoads() {
	}

}
