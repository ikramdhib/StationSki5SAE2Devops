package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.SubscriptionDTO;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISubscriptionServices;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Tag(name = "\uD83D\uDC65 Subscription Management")
@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionRestController {

    private static final String ADD_SUBSCRIPTION_URL = "/add";
    private static final String GET_SUBSCRIPTION_BY_ID_URL = "/get/{id-subscription}";
    private static final String GET_SUBSCRIPTIONS_BY_TYPE_URL = "/all/{typeSub}";
    private static final String UPDATE_SUBSCRIPTION_URL = "/update";
    private static final String GET_SUBSCRIPTIONS_BY_DATES_URL = "/all/{date1}/{date2}";

    private final ISubscriptionServices subscriptionServices;

    // Méthode privée pour convertir SubscriptionDTO en Subscription
    private Subscription convertToSubscription(SubscriptionDTO subscriptionDTO) {
        return Subscription.builder()
                .startDate(subscriptionDTO.getStartDate())
                .endDate(subscriptionDTO.getEndDate())
                .price(subscriptionDTO.getPrice())
                .build();
    }

    @Operation(description = "Add Subscription")
    @PostMapping(ADD_SUBSCRIPTION_URL)
    public ResponseEntity<Subscription> addSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = convertToSubscription(subscriptionDTO);
        Subscription addedSubscription = subscriptionServices.addSubscription(subscription);
        return new ResponseEntity<>(addedSubscription, HttpStatus.CREATED);
    }

    @Operation(description = "Retrieve Subscription by Id")
    @GetMapping(GET_SUBSCRIPTION_BY_ID_URL)
    public ResponseEntity<Subscription> getById(@PathVariable("id-subscription") Long numSubscription) {
        Subscription subscription = subscriptionServices.retrieveSubscriptionById(numSubscription);
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Subscriptions by Type")
    @GetMapping(GET_SUBSCRIPTIONS_BY_TYPE_URL)
    public ResponseEntity<Set<Subscription>> getSubscriptionsByType(@PathVariable("typeSub") TypeSubscription typeSubscription) {
        Set<Subscription> subscriptions = subscriptionServices.getSubscriptionByType(typeSubscription);
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @Operation(description = "Update Subscription")
    @PutMapping(UPDATE_SUBSCRIPTION_URL)
    public ResponseEntity<Subscription> updateSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = convertToSubscription(subscriptionDTO);
        Subscription updatedSubscription = subscriptionServices.updateSubscription(subscription);
        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Subscriptions created between two dates")
    @GetMapping(GET_SUBSCRIPTIONS_BY_DATES_URL)
    public ResponseEntity<List<Subscription>> getSubscriptionsByDates(@PathVariable("date1") LocalDate startDate,
                                                                      @PathVariable("date2") LocalDate endDate) {
        List<Subscription> subscriptions = subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate);
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}
