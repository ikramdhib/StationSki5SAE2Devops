package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubscriptionServicesImplJunitTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.of(2024, 1, 1));

        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        Subscription result = subscriptionServices.addSubscription(subscription);

        assertEquals(subscription.getStartDate().plusYears(1), result.getEndDate());
        verify(subscriptionRepository).save(subscription);
    }

    @Test
    void updateSubscription() {
        Subscription subscription = new Subscription();
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        Subscription result = subscriptionServices.updateSubscription(subscription);

        assertEquals(subscription, result);
        verify(subscriptionRepository).save(subscription);
    }

    @Test
    void retrieveSubscriptionById() {
        Subscription subscription = new Subscription();
        Long id = 1L;
        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        Subscription result = subscriptionServices.retrieveSubscriptionById(id);

        assertEquals(subscription, result);
        verify(subscriptionRepository).findById(id);
    }

    @Test
    void getSubscriptionByType() {
        Set<Subscription> subscriptions = new HashSet<>();
        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(TypeSubscription.ANNUAL)).thenReturn(subscriptions);

        Set<Subscription> result = subscriptionServices.getSubscriptionByType(TypeSubscription.ANNUAL);

        assertEquals(subscriptions, result);
        verify(subscriptionRepository).findByTypeSubOrderByStartDateAsc(TypeSubscription.ANNUAL);
    }

    @Test
    void retrieveSubscriptionsByDates() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription());
        when(subscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate)).thenReturn(subscriptions);

        List<Subscription> result = subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate);

        assertEquals(subscriptions, result);
        verify(subscriptionRepository).getSubscriptionsByStartDateBetween(startDate, endDate);
    }

    @Test
    void retrieveSubscriptions() {
        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setEndDate(LocalDate.of(2024, 12, 31));

        Skier skier = new Skier();
        skier.setFirstName("John");
        skier.setLastName("Doe");

        List<Subscription> t = new ArrayList<>();
        t.add(subscription);
        when(subscriptionRepository.findDistinctOrderByEndDateAsc()).thenReturn(t);
        when(skierRepository.findBySubscription(subscription)).thenReturn(skier);

        List<Subscription> result = subscriptionServices.retrieveSubscriptions();

        assertNull(result);  // retrieveSubscriptions returns null
        verify(subscriptionRepository).findDistinctOrderByEndDateAsc();
        verify(skierRepository).findBySubscription(subscription);
    }

    @Test
    void showMonthlyRecurringRevenue() {
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.MONTHLY)).thenReturn(1000f);
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.SEMESTRIEL)).thenReturn(6000f);
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.ANNUAL)).thenReturn(12000f);

        subscriptionServices.showMonthlyRecurringRevenue();

        verify(subscriptionRepository).recurringRevenueByTypeSubEquals(TypeSubscription.MONTHLY);
        verify(subscriptionRepository).recurringRevenueByTypeSubEquals(TypeSubscription.SEMESTRIEL);
        verify(subscriptionRepository).recurringRevenueByTypeSubEquals(TypeSubscription.ANNUAL);
    }
}
