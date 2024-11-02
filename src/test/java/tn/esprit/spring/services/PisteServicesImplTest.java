package tn.esprit.spring.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.*;



import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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


        Piste savedPiste = pisteServices.addPiste(pisteA);


        assertNotNull(savedPiste);
        assertEquals("Green Valley", savedPiste.getNamePiste());
        assertEquals(Color.RED, savedPiste.getColor());
        verify(pisteRepository, times(1)).save(pisteA);
    }

    @Test
    void removePiste() {

        Long pisteId = 1L;

        pisteServices.removePiste(pisteId);

        verify(pisteRepository, times(1)).deleteById(pisteId);
    }

    @Test
    void retrievePiste() {
        Long pisteId = 1L;

        Mockito.when(pisteRepository.findById(pisteId)).thenReturn(Optional.of(pisteA));

        Piste retrievedPiste = pisteServices.retrievePiste(pisteId);

        assertNotNull(retrievedPiste);
        assertEquals("Green Valley", retrievedPiste.getNamePiste());
        assertEquals(Color.RED, retrievedPiste.getColor());
        verify(pisteRepository, times(1)).findById(pisteId);

    }
}