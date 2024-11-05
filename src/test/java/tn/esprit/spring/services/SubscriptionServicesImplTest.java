package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.dto.SubscriptionDTO;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
 class SubscriptionServicesImplTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;
    @InjectMocks
    private final SubscriptionMapper subscriptionMapper = new SubscriptionMapper();

    private Subscription subscription;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.MONTHLY);
        subscription.setStartDate(LocalDate.now());
    }
    @Test
    void testToDTO() {
        // Given
        Subscription subscription2 = new Subscription();
        subscription2.setNumSub(1L);
        subscription2.setStartDate(LocalDate.of(2024, 11, 1));
        subscription2.setEndDate(LocalDate.of(2024, 12, 1));
        subscription2.setPrice(100.0f);
        subscription2.setTypeSub(TypeSubscription.MONTHLY);

        // When
        SubscriptionDTO subscriptionDTO = subscriptionMapper.toDTO(subscription2);

        // Then
        assertEquals(subscription2.getNumSub(), subscriptionDTO.getNumSub());
        assertEquals(subscription2.getStartDate(), subscriptionDTO.getStartDate());
        assertEquals(subscription2.getEndDate(), subscriptionDTO.getEndDate());
        assertEquals(subscription2.getPrice(), subscriptionDTO.getPrice());
        assertEquals(subscription2.getTypeSub(), subscriptionDTO.getTypeSub());
    }

    @Test
    void testToEntity() {
        // Given
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setNumSub(1L);
        subscriptionDTO.setStartDate(LocalDate.of(2024, 11, 1));
        subscriptionDTO.setEndDate(LocalDate.of(2024, 12, 1));
        subscriptionDTO.setPrice(100.0f);
        subscriptionDTO.setTypeSub(TypeSubscription.MONTHLY);

        // When
        Subscription subscription1 = subscriptionMapper.toEntity(subscriptionDTO);

        // Then
        assertEquals(subscriptionDTO.getNumSub(), subscription1.getNumSub());
        assertEquals(subscriptionDTO.getStartDate(), subscription1.getStartDate());
        assertEquals(subscriptionDTO.getEndDate(), subscription1.getEndDate());
        assertEquals(subscriptionDTO.getPrice(), subscription1.getPrice());
        assertEquals(subscriptionDTO.getTypeSub(), subscription1.getTypeSub());
    }
    @Test
    void addSubscription() {
        // Setup
        subscription.setEndDate(subscription.getStartDate().plusMonths(1));
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Test
        Subscription result = subscriptionServices.addSubscription(subscription);

        // Assert
        assertNotNull(result);
        assertEquals(subscription.getTypeSub(), result.getTypeSub());
        assertEquals(subscription.getStartDate().plusMonths(1), result.getEndDate());
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void updateSubscription() {
        // Setup
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Test
        Subscription result = subscriptionServices.updateSubscription(subscription);

        // Assert
        assertNotNull(result);
        assertEquals(subscription.getStartDate(), result.getStartDate());
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void retrieveSubscriptionById() {
        // Setup
        Long id = 1L;
        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        // Test
        Subscription result = subscriptionServices.retrieveSubscriptionById(id);

        // Assert
        assertNotNull(result);
        assertEquals(subscription.getTypeSub(), result.getTypeSub());
        verify(subscriptionRepository, times(1)).findById(id);
    }

    @Test
    void getSubscriptionByType() {
        // Setup
        TypeSubscription type = TypeSubscription.MONTHLY;
        Set<Subscription> mockSubscriptions = Collections.singleton(subscription);  // Use Collections.singleton for Java 8 compatibility
        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(type)).thenReturn(mockSubscriptions);

        // Test
        Set<Subscription> result = subscriptionServices.getSubscriptionByType(type);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(subscriptionRepository, times(1)).findByTypeSubOrderByStartDateAsc(type);
    }
    @Test
    void retrieveSubscriptionsByDates() {
        // Setup
        LocalDate date1 = LocalDate.now().minusDays(10);
        LocalDate date2 = LocalDate.now().plusDays(10);
        List<Subscription> mockSubscriptions = Arrays.asList(subscription);  // Use Arrays.asList for Java 8 compatibility
        when(subscriptionRepository.getSubscriptionsByStartDateBetween(date1, date2)).thenReturn(mockSubscriptions);

        // Test
        List<Subscription> result = subscriptionServices.retrieveSubscriptionsByDates(date1, date2);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(subscriptionRepository, times(1)).getSubscriptionsByStartDateBetween(date1, date2);
    }


    @Test
    void retrieveSubscriptions() {
        // Setup
        Long numSub = 1L;
        Subscription subscription1 = new Subscription();
        subscription1.setNumSub(numSub);
        subscription1.setStartDate(LocalDate.now());
        subscription1.setTypeSub(TypeSubscription.MONTHLY);

        subscriptionServices.addSubscription(subscription1);

        List<Subscription> mockSubscriptions = new ArrayList<>();
        mockSubscriptions.add(subscription1);
        when(subscriptionRepository.findDistinctOrderByEndDateAsc()).thenReturn(mockSubscriptions);

        // Créez une instance de Skier
        Skier skierDTO = new Skier();
        skierDTO.setFirstName("Joe");
        skierDTO.setLastName("DOhn");

        // Ici, utilisez la même instance de subscription pour la vérification
        when(skierRepository.findBySubscription(subscription1)).thenReturn(skierDTO);

        // Test
        subscriptionServices.retrieveSubscriptions();

        // Vérifiez les interactions
        verify(subscriptionRepository, times(1)).findDistinctOrderByEndDateAsc();
        verify(skierRepository, times(1)).findBySubscription(subscription1); // Utilisez subscription1 ici
    }


    @Test
    void showMonthlyRecurringRevenue() {
        // Setup
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.MONTHLY)).thenReturn(100f);
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.SEMESTRIEL)).thenReturn(600f);
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.ANNUAL)).thenReturn(1200f);

        // Test
        subscriptionServices.showMonthlyRecurringRevenue();

        // Assert that logs revenue (you may use a log capturing utility for testing log output)
        verify(subscriptionRepository, times(1)).recurringRevenueByTypeSubEquals(TypeSubscription.MONTHLY);
        verify(subscriptionRepository, times(1)).recurringRevenueByTypeSubEquals(TypeSubscription.SEMESTRIEL);
        verify(subscriptionRepository, times(1)).recurringRevenueByTypeSubEquals(TypeSubscription.ANNUAL);
    }
}
