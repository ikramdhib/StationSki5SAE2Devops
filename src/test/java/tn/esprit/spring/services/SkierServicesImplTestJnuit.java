package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SkierServicesImplTestJnuit {

    @Autowired
    private SkierServicesImpl skierService;

    @Autowired
    private ISkierRepository skierRepository;

    @Autowired
    private ISubscriptionRepository subscriptionRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IPisteRepository pisteRepository;


    @BeforeEach
    void setUp() {
        // Optional: Clean up the database or initialize necessary data
        skierRepository.deleteAll();
        subscriptionRepository.deleteAll();
    }
    @Test
    void testAddSkier() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());
        skier.setSubscription(subscription);

        // Call the method under test
        Skier savedSkier = skierService.addSkier(skier);

        // Assert the results
        assertNotNull(savedSkier); // The skier should not be null
        assertNotNull(savedSkier.getSubscription().getEndDate()); // The subscription end date should not be null
        assertEquals(LocalDate.now().plusYears(1), savedSkier.getSubscription().getEndDate()); // Check end date
    }



    @Test
    void testAssignSkierToSubscription() {
        Skier skier = new Skier();
        skierRepository.save(skier);

        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.MONTHLY);
        subscriptionRepository.save(subscription);

        Skier result = skierService.assignSkierToSubscription(skier.getNumSkier(), subscription.getNumSub());

        assertNotNull(result);
        assertEquals(subscription.getNumSub(), result.getSubscription().getNumSub());
    }


    @Test
    void testRemoveSkier() {
        Skier skier = new Skier();
        skierRepository.save(skier);

        skierService.removeSkier(skier.getNumSkier());

        Optional<Skier> result = skierRepository.findById(skier.getNumSkier());
        assertFalse(result.isPresent());
    }



    @Test
    void testRetrieveSkiersBySubscriptionType() {
        Skier skier1 = new Skier();
        Skier skier2 = new Skier();

        Subscription subscription1 = new Subscription();
        subscription1.setTypeSub(TypeSubscription.ANNUAL);
        skier1.setSubscription(subscription1);

        Subscription subscription2 = new Subscription();
        subscription2.setTypeSub(TypeSubscription.ANNUAL);
        skier2.setSubscription(subscription2);

        skierRepository.save(skier1);
        skierRepository.save(skier2);

         List<Skier> skiers = skierService.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        assertEquals(2, skiers.size());
    }
}
