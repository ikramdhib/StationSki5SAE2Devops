package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.dto.SubscriptionDTO;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.SubscriptionMapper;
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

    private final ISubscriptionServices subscriptionServices;
    private final SubscriptionMapper subscriptionMapper;

    @Operation(description = "Add Subscription")
    @PostMapping(value = "/add")
    public ResponseEntity<SubscriptionDTO> addSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);
        Subscription savedSubscription = subscriptionServices.addSubscription(subscription);
        SubscriptionDTO savedSubscriptionDTO = subscriptionMapper.toDTO(savedSubscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubscriptionDTO);
    }

    @Operation(description = "Retrieve Subscription by Id")
    @GetMapping(value = "/get/{id-subscription}")
    public Subscription getById(@PathVariable("id-subscription") Long numSubscription){
        return subscriptionServices.retrieveSubscriptionById(numSubscription);
    }

    @Operation(description = "Retrieve Subscriptions by Type")
    @GetMapping(value = "/all/{typeSub}")
    public Set<Subscription> getSubscriptionsByType(@PathVariable("typeSub") TypeSubscription typeSubscription){
        return subscriptionServices.getSubscriptionByType(typeSubscription);
    }
    @Operation(description = "Update Subscription")
    @PutMapping(value = "/update")
    public ResponseEntity<SubscriptionDTO> updateSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);
        Subscription updatedSubscription = subscriptionServices.updateSubscription(subscription);
        SubscriptionDTO updatedSubscriptionDTO = subscriptionMapper.toDTO(updatedSubscription);
        return ResponseEntity.ok(updatedSubscriptionDTO);
    }

    @Operation(description = "Retrieve Subscriptions created between two dates")
    @GetMapping(value = "/all/{date1}/{date2}")
    public List<Subscription> getSubscriptionsByDates(@PathVariable("date1") LocalDate startDate,
                                                      @PathVariable("date2") LocalDate endDate){
        return subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate);
    }
}
