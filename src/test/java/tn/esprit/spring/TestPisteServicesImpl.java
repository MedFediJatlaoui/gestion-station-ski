package tn.esprit.spring;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.*;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
@ExtendWith(MockitoExtension.class)

public class TestPisteServicesImpl {
    @Mock
    private IPisteRepository pisteRepository;


    @InjectMocks
    private PisteServicesImpl pisteServices;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllPistes() {

        List<Piste> pistes = new ArrayList<>();
        Piste piste1 = new Piste();
        piste1.setNumPiste(1L);
        pistes.add(piste1);

        when(pisteRepository.findAll()).thenReturn(pistes);


        List<Piste> result = pisteServices.retrieveAllPistes();

        // Vérifiez si le résultat est correct.
        assertEquals(1, result.size());
    }

    @Test
    public void testAddPiste() {
        // Créez une piste fictive pour le test.
        Piste piste = new Piste();
        piste.setNumPiste(1L);

        // Configurez le comportement de votre objet mock pisteRepository.
        when(pisteRepository.save(piste)).thenReturn(piste);

        // Appelez la méthode de service que vous souhaitez tester.
        Piste result = pisteServices.addPiste(piste);

        // Vérifiez si le résultat est correct.
        assertEquals(1L, result.getNumPiste().longValue());
    }

    @Test
    public void testRemovePiste() {
        // Appelez la méthode de service que vous souhaitez tester.
        pisteServices.removePiste(1L);

        // Vérifiez si la méthode de suppression a été appelée sur l'objet mock pisteRepository.
        verify(pisteRepository).deleteById(1L);
    }

    @Test
    public void testRetrievePiste() {
        // Créez une piste fictive pour le test.
        Piste piste = new Piste();
        piste.setNumPiste(1L);

        // Configurez le comportement de votre objet mock pisteRepository.
        when(pisteRepository.findById(1L)).thenReturn(Optional.of(piste));

        // Appelez la méthode de service que vous souhaitez tester.
        Piste result = pisteServices.retrievePiste(1L);

        // Vérifiez si le résultat est correct.
        assertEquals(1L, result.getNumPiste().longValue());
    }
}
