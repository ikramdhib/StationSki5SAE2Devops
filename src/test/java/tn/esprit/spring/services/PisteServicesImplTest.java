package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.dto.PisteDTO;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.*;


import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class PisteServicesImplTest {

    @InjectMocks
    private PisteServicesImpl pisteServices;


    @Mock
    private IPisteRepository pisteRepository;

    Piste pisteA = Piste.builder().namePiste("Green Valley").color(Color.RED).length(500).build();

    Piste pisteT = Piste.builder().namePiste("Green").color(Color.GREEN).length(100).build();

    Piste pisteB = Piste.builder().namePiste("Green Valley").color(Color.BLUE).length(800).build();

    @Test
    void testPisteDTO() {
        PisteDTO pisteDTO = new PisteDTO();

        Long numPiste = 1L;
        String namePiste = "Piste 1";
        Color color = Color.valueOf("GREEN");
        int length = 200;
        int slope = 15;
        Set<Skier> skiers = new HashSet<>();

        // Utilisation des setters
        pisteDTO.setNumPiste(numPiste);
        pisteDTO.setNamePiste(namePiste);
        pisteDTO.setColor(color);
        pisteDTO.setLength(length);
        pisteDTO.setSlope(slope);
        pisteDTO.setSkiers(skiers);

        // Assertions pour vérifier que les getters retournent les valeurs correctes
        assertEquals(numPiste, pisteDTO.getNumPiste(), "Le numéro de piste doit correspondre");
        assertEquals(namePiste, pisteDTO.getNamePiste(), "Le nom de la piste doit correspondre");
        assertEquals(color, pisteDTO.getColor(), "La couleur doit correspondre");
        assertEquals(length, pisteDTO.getLength(), "La longueur doit correspondre");
        assertEquals(slope, pisteDTO.getSlope(), "La pente doit correspondre");
        assertEquals(skiers, pisteDTO.getSkiers(), "Le set de skieurs doit correspondre");
    }

    @Test
    void retrieveAllPistes() {

        List<Piste> pistes = new ArrayList<>();
        pistes.add(pisteA);
        pistes.add(pisteT);
        pistes.add(pisteB);


        Mockito.when(pisteRepository.findAll()).thenReturn(pistes);


        List<Piste> retrievedPistes = pisteServices.retrieveAllPistes();


        assertNotNull(retrievedPistes);
        assertEquals(3, retrievedPistes.size());
        assertTrue(retrievedPistes.contains(pisteA));
        assertTrue(retrievedPistes.contains(pisteT));
        assertTrue(retrievedPistes.contains(pisteB));
    }

    @Test
    void addPiste() {

        Mockito.when(pisteRepository.save(Mockito.any(Piste.class))).thenReturn(pisteA);
        Piste pisteDTO = new Piste();
        pisteDTO.setNamePiste("Green Valley");
        pisteDTO.setColor(Color.RED);
        pisteDTO.setLength(500);

        Piste savedPiste = pisteServices.addPiste(pisteDTO);


        assertNotNull(savedPiste);
        assertEquals("Green Valley", savedPiste.getNamePiste());
        assertEquals(Color.RED, savedPiste.getColor());
        verify(pisteRepository, times(1)).save(pisteDTO);
    }

    @Test
    void removePiste() {

        Long pisteId = 1L;

        // Call the service method
        pisteServices.removePiste(pisteId);

        // Verify that the deleteById method was called with the correct parameter
        verify(pisteRepository, times(1)).deleteById(pisteId);
    }

    @Test
    void retrievePiste() {
        Long pisteId = 1L;

        // Mock the behavior of findById method
        Mockito.when(pisteRepository.findById(pisteId)).thenReturn(Optional.of(pisteA));

        // Call the service method
        Piste retrievedPiste = pisteServices.retrievePiste(pisteId);

        // Assertions to verify the behavior
        assertNotNull(retrievedPiste);
        assertEquals("Green Valley", retrievedPiste.getNamePiste());
        assertEquals(Color.RED, retrievedPiste.getColor());
        verify(pisteRepository, times(1)).findById(pisteId);

    }

}