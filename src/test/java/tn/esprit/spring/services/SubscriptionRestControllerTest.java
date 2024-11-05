package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.spring.controllers.SubscriptionRestController;
import tn.esprit.spring.dto.SubscriptionDTO;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.SubscriptionMapper;
import tn.esprit.spring.entities.TypeSubscription;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionRestControllerTest {

    @Mock
    private ISubscriptionServices subscriptionServices;

    @Mock
    private SubscriptionMapper subscriptionMapper;

    @InjectMocks
    private SubscriptionRestController subscriptionRestController;

    private Subscription subscription;
    private SubscriptionDTO subscriptionDTO;

    @BeforeEach
    void setUp() {
        subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setTypeSub(TypeSubscription.MONTHLY);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(subscription.getStartDate().plusMonths(1));

        subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setNumSub(1L);
        subscriptionDTO.setTypeSub(TypeSubscription.MONTHLY);
        subscriptionDTO.setStartDate(LocalDate.now());
        subscriptionDTO.setEndDate(subscriptionDTO.getStartDate().plusMonths(1));
    }

    @Test
    void testAddSubscription() {
        when(subscriptionMapper.toEntity(any(SubscriptionDTO.class))).thenReturn(subscription);
        when(subscriptionServices.addSubscription(any(Subscription.class))).thenReturn(subscription);
        when(subscriptionMapper.toDTO(any(Subscription.class))).thenReturn(subscriptionDTO);

        ResponseEntity<SubscriptionDTO> response = subscriptionRestController.addSubscription(subscriptionDTO);

        assertNotNull(response, "La réponse ne doit pas être null");
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Le statut HTTP doit être CREATED");
        assertEquals(subscriptionDTO, response.getBody(), "Le DTO retourné ne correspond pas au DTO attendu");

        verify(subscriptionServices, times(1)).addSubscription(any(Subscription.class));
    }

    @Test
    void testGetById() {
        when(subscriptionServices.retrieveSubscriptionById(1L)).thenReturn(subscription);

        Subscription result = subscriptionRestController.getById(1L);

        assertNotNull(result, "L'abonnement retourné ne doit pas être null");
        assertEquals(subscription, result, "L'abonnement retourné doit correspondre à celui attendu");

        verify(subscriptionServices, times(1)).retrieveSubscriptionById(1L);
    }

    @Test
    void testGetSubscriptionsByType() {
        Set<Subscription> subscriptions = Set.of(subscription);
        when(subscriptionServices.getSubscriptionByType(TypeSubscription.MONTHLY)).thenReturn(subscriptions);

        Set<Subscription> result = subscriptionRestController.getSubscriptionsByType(TypeSubscription.MONTHLY);

        assertNotNull(result, "La liste des abonnements ne doit pas être null");
        assertFalse(result.isEmpty(), "La liste des abonnements doit contenir des éléments");

        verify(subscriptionServices, times(1)).getSubscriptionByType(TypeSubscription.MONTHLY);
    }

    @Test
    void testUpdateSubscription() {
        when(subscriptionMapper.toEntity(any(SubscriptionDTO.class))).thenReturn(subscription);
        when(subscriptionServices.updateSubscription(any(Subscription.class))).thenReturn(subscription);
        when(subscriptionMapper.toDTO(any(Subscription.class))).thenReturn(subscriptionDTO);

        ResponseEntity<SubscriptionDTO> response = subscriptionRestController.updateSubscription(subscriptionDTO);

        assertNotNull(response, "La réponse ne doit pas être null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Le statut HTTP doit être OK");
        assertEquals(subscriptionDTO, response.getBody(), "Le DTO retourné ne correspond pas au DTO attendu");

        verify(subscriptionServices, times(1)).updateSubscription(any(Subscription.class));
    }

    @Test
    void testGetSubscriptionsByDates() {
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(10);
        List<Subscription> subscriptions = Arrays.asList(subscription);
        when(subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate)).thenReturn(subscriptions);

        List<Subscription> result = subscriptionRestController.getSubscriptionsByDates(startDate, endDate);

        assertNotNull(result, "La liste des abonnements ne doit pas être null");
        assertEquals(1, result.size(), "La liste des abonnements doit contenir 1 élément");

        verify(subscriptionServices, times(1)).retrieveSubscriptionsByDates(startDate, endDate);
    }
}
