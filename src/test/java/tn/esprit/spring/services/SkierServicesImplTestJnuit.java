package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // Utiliser le profil de test
class SkierServicesImplTestJnuit {

    @Autowired
    private SkierServicesImpl skierService;

    @Autowired
    private ISkierRepository skierRepository;

    @Autowired
    private ISubscriptionRepository subscriptionRepository;

    @BeforeEach
    void setUp() {
        // Optionnel: Nettoyez la base de données ou initialisez les données nécessaires
    }

    @Test
    void testAddSkier() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());
        skier.setSubscription(subscription);

        // Appeler la méthode à tester
        Skier savedSkier = skierService.addSkier(skier);

        // Vérifier les assertions
        assertNotNull(savedSkier); // Le skieur ne devrait pas être null
        assertNotNull(savedSkier.getSubscription().getEndDate()); // La date de fin de l'abonnement ne devrait pas être null
        assertEquals(LocalDate.now().plusYears(1), savedSkier.getSubscription().getEndDate()); // Vérifiez que la date de fin est correcte
    }

    @Test
    void testRetrieveSkier() {
        Long numSkier = 1L; // ID d'un skieur existant

        // Créez un skieur fictif et enregistrez-le
        Skier skier = new Skier();
        skier.setNumSkier(numSkier);
        skierRepository.save(skier);

        // Appeler la méthode à tester
        Optional<Skier> result = Optional.ofNullable(skierService.retrieveSkier(numSkier));

        // Vérifier les assertions
        assertTrue(result.isPresent()); // Le skieur devrait être présent
        assertEquals(numSkier, result.get().getNumSkier()); // Vérifiez que l'ID correspond
    }

    // Ajoutez d'autres tests selon vos besoins

}
